package com.example.android.horoka

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.horoka.api.apiService
import com.example.android.horoka.db.HorokaDb
import timber.log.Timber

class DownloadWorker(val context: Context, downloadWorkerParameters: WorkerParameters) : CoroutineWorker(context, downloadWorkerParameters){
    override suspend fun doWork(): Result {
//      sleep was introduced for testing purposes
//        sleep()
        val dbDao = HorokaDb.getInstance(context).horokaDao
        Timber.v("DB instance acquired")
        val photos = apiService.getPhotos(context.getString(R.string.accessKey), "love", 1)
        dbDao.insertPhoto(*photos.toTypedArray())
        Timber.v("Acquired image URL from Unsplash")
        val photo = photos[0]

        downloadImageFromUri(photo.id.plus(".jpg"), photo.raw_url, context, inputData.getInt("width",600))
        Timber.v("Photo downloaded")
        notify(context)
        return Result.success()
    }
}

//https://images.unsplash.com/photo-1558980664-10e7170b5df9