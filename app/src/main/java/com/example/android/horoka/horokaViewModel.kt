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

class horokaViewModel(application: Application) : AndroidViewModel(application){

    private val CHANNEL_ID = application.getString(R.string.horokaNotificationChannel)
    init {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val descriptionText = application.getString(R.string.notificationChannelDescription)
            val importance = NotificationManager.IMPORTANCE_MIN
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance).apply {
                description = descriptionText
            }
        // Register the channel with the system
            val notificationManager: NotificationManager = application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    // Create an explicit intent for an Activity in your app
    private val intent = Intent(application, MainActivity::class.java).apply {
//        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    private val pendingIntent: PendingIntent = PendingIntent.getActivity(application, 0, intent, 0)

    private var notificationBuilder = NotificationCompat.Builder(application, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Title")
        .setContentText("Description")
        .setPriority(NotificationCompat.PRIORITY_MIN)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
    fun notifyMe() {
        NotificationManagerCompat.
    }
}