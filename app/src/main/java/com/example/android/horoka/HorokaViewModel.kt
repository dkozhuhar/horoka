package com.example.android.horoka

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.android.horoka.api.apiService
import com.example.android.horoka.db.HorokaDb
import com.example.android.horoka.db.HorokaPhoto
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.concurrent.TimeUnit


class HorokaViewModel(val app: Application) : AndroidViewModel(app) {

    private val vieModelJob = Job()

    private val dbDao = HorokaDb.getInstance(app).horokaDao

    private val viewModelScope = CoroutineScope(Dispatchers.IO + vieModelJob)

    val horokaPhotos = dbDao.getAllPhotos()
    //First time population DB
    init {
        viewModelScope.launch {
//            Deleting for testing purposes
//            dbDao.deleteAll()
            if (dbDao.countPhotos() == 0) {
                getPhotosFromUnsplash()
            }
        }
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()
        val periodicWorkRequest = PeriodicWorkRequestBuilder<DownloadWorker>(15,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(app).enqueueUniquePeriodicWork("GET_LOVE_EVERYDAY",ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest)
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
        val downloadFolder = app.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val resolver = app.contentResolver
        val downloadRequest =
            DownloadManager.Request(Uri.parse(photo.raw_url + "&q=85&fm=jpg&crop=entropy&cs=srgb"))
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setTitle(photo.alt_description)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/test.jpg")

        val downloadManager = app.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadID = downloadManager.enqueue(downloadRequest)
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