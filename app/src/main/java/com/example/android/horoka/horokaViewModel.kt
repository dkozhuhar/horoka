package com.example.android.horoka

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import kotlin.random.Random

class horokaViewModel : ViewModel(){

    private var _listOfPhoto = MutableLiveData<List<UnsplashPhoto>>()

    val listOfPhoto : LiveData<List<UnsplashPhoto>>
    get() = _listOfPhoto

    fun notifyMe(context: Context) {
        val downloadRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        WorkManager.getInstance(context).enqueue(downloadRequest)
    }




}