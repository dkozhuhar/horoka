package com.example.android.horoka

import android.Manifest
import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.android.horoka.api.apiService
import com.example.android.horoka.db.HorokaDb
import com.example.android.horoka.db.HorokaPhoto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit


class HorokaViewModel(val app: Application) : AndroidViewModel(app) {

    private val vieModelJob = Job()

    private val dbDao = HorokaDb.getInstance(app).horokaDao

    private val viewModelScope = CoroutineScope(Dispatchers.IO + vieModelJob)

    private val sharedPref =
        app.getSharedPreferences(app.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    val horokaPhotos = dbDao.getAllPhotos()

    init {
//      Getting thumbnailwidth
        val displayMetrics = DisplayMetrics()
        (app.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(
            displayMetrics
        )
        val thumbnailWidth = displayMetrics.widthPixels / 2
//      First time population DB
        viewModelScope.launch {
//            Deleting for testing purposes
//            dbDao.deleteAll()
            if (dbDao.countPhotos() == 0) {
                getPhotosFromUnsplash()
            }
//            creating or adding local thumbnails repository
            val files: Array<String> = app.fileList()
//            Purging local repo for testing purposes
//            for (file in files){
//                app.deleteFile(file)
//            }


            val photoIdsAndUrls = dbDao.getAllPhotoIdsAndUrls()
            for (photoIdAndUrl in photoIdsAndUrls) {
                val fileName = photoIdAndUrl.id.plus(".jpg")
                if (!files.contains(fileName)) {
                    downloadImageFromUri(fileName, photoIdAndUrl.raw_url, app, thumbnailWidth)
                }
            }
        }


        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workerParameters = Data.Builder()
        workerParameters.putInt("thumbnailWidth", thumbnailWidth)
        val periodicWorkRequest = PeriodicWorkRequestBuilder<DownloadWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(workerParameters.build())
            .build()
        WorkManager.getInstance(app).enqueueUniquePeriodicWork(
            "GET_LOVE_EVERYDAY",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
//        sleep()

    }

    fun notifyMe() {
        val downloadRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        WorkManager.getInstance(app).enqueue(downloadRequest)
    }

    private suspend fun getPhotosFromUnsplash() {
        Timber.i("getPhotosFromUnsplash called")
        try {
            val photos = apiService.getPhotos(app.getString(R.string.accessKey), "love", 10)
            dbDao.insertPhoto(*photos.toTypedArray())
            Timber.i(photos.toString())
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }


    suspend fun getPhotoById(id: String): HorokaPhoto? {
        return dbDao.getPhotoById(id)
    }

    fun savePhoto(photo: HorokaPhoto) {
        val downloadRequest =
            DownloadManager.Request(Uri.parse(photo.raw_url + "&q=85&fm=jpg&crop=entropy&cs=srgb"))
//                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setTitle(photo.alt_description)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "")

        val downloadManager = app.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadID = downloadManager.enqueue(downloadRequest)
        Timber.v("Download " + downloadID.toString() + " started")

        viewModelScope.launch {
            apiService.hitDownloadLink(
                photo.download_location,
                app.getString(R.string.accessKey)
            )
        }
    }

    fun getNotificationTitle(context: Context): String {
        return sharedPref.getString(
            context.getString(R.string.who_loves_you_key),
            context.getString(R.string.notification_title)
        ) ?: context.getString(R.string.notification_title)
    }

    fun setNotificationTitle(context: Context, newTitle: String) {
        with(sharedPref.edit()) {
            putString(context.getString(R.string.who_loves_you_key), newTitle)
            apply()
        }
    }

    override fun onCleared() {
        super.onCleared()
        vieModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HorokaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HorokaViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}