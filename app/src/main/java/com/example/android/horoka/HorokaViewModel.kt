package com.example.android.horoka

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.android.horoka.api.apiService
import com.example.android.horoka.db.HorokaDb
import kotlinx.coroutines.*
import timber.log.Timber


class HorokaViewModel(val app: Application) : AndroidViewModel(app) {

    private val vieModelJob = Job()

    private val dbDao = HorokaDb.getInstance(app).horokaDao

    private val viewModelScope = CoroutineScope(Dispatchers.IO + vieModelJob)

    val horokaPhotos = dbDao.getAllPhotos()

    fun notifyMe() {
        val downloadRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        WorkManager.getInstance(app).enqueue(downloadRequest)
    }

    fun getPhotosFromUnsplash() {
        Timber.i("getPhotosFromUnsplash called")
        viewModelScope.launch {
            if (dbDao.getAllPhotos().value?.isEmpty() ?: true) {
                try {
                    val photos = apiService.getPhotos(app.getString(R.string.accessKey), "love", 10)
                    dbDao.insertPhoto(*photos.toTypedArray())
                    Timber.i(photos.toString())
                } catch (t: Throwable) {
                    Timber.e(t)
                }
            }
        }
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