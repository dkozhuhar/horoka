package com.example.android.horoka

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber

class DownloadWorker(val context: Context, downloadWorkerParameters: WorkerParameters) : Worker(context, downloadWorkerParameters){
    override fun doWork(): Result {

        sleep()
        notify(context)
        return Result.success()
    }
}

//https://images.unsplash.com/photo-1558980664-10e7170b5df9