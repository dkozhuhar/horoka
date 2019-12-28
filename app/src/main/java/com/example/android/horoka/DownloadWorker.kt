package com.example.android.horoka

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.android.horoka.api.apiService
import com.example.android.horoka.db.HorokaDb
import timber.log.Timber

class DownloadWorker(val context: Context, downloadWorkerParameters: WorkerParameters) : CoroutineWorker(context, downloadWorkerParameters){
    override suspend fun doWork(): Result {
//      sleep was introduced for testing purposes
        sleep()
        val dbDao = HorokaDb.getInstance(context).horokaDao

        val photo = apiService.getPhotos(context.getString(R.string.accessKey), "love", 1)
        dbDao.insertPhoto(*photo.toTypedArray())

        notify(context)
        return Result.success()
    }
}

//https://images.unsplash.com/photo-1558980664-10e7170b5df9