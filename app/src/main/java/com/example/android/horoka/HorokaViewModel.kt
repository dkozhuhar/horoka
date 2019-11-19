package com.example.android.horoka

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.android.horoka.api.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber


class HorokaViewModel() : ViewModel(){

    val vieModelJob = Job()

    val viewModelScope = CoroutineScope(Dispatchers.Main + vieModelJob)

    fun notifyMe(context: Context) {
        val downloadRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        WorkManager.getInstance(context).enqueue(downloadRequest)
    }

    fun getPhotosFromUnsplash(context: Context){
        Timber.i("getPhotosFromUnsplash called")
        viewModelScope.launch {
            try {
                val todayPhoto = apiService.getPhotos(context.getString(R.string.accessKey),"love",10)
                Timber.i(todayPhoto.toString())
            }
            catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }
}