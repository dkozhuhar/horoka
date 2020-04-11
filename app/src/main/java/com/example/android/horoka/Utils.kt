package com.example.android.horoka

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import timber.log.Timber
import java.io.File
import java.net.URL


/*
Notification Utils
*/

private fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val CHANNEL_ID = context.getString(R.string.horokaNotificationChannel)
        val descriptionText = context.getString(R.string.notificationChannelDescription)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun notify(context: Context) {
    createNotificationChannel(context)
    // Create an explicit intent for an Activity in your app
    val intent = Intent(context, MainActivity::class.java).apply {
        //        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
    val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    val notificationTitle = sharedPref.getString(context.getString(R.string.who_loves_you_key),context.getString(R.string.notification_title)) ?: context.getString(R.string.notification_title)
    val notificationBuilder = NotificationCompat.Builder(context, context.getString(R.string.horokaNotificationChannel))
        .setSmallIcon(R.drawable.ic_favorite_black_24dp)
        .setContentTitle(notificationTitle)
        .setContentText(context.getString(R.string.notification_description))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notify(1, notificationBuilder.build())
    }
}


fun downloadImageFromUri(filename: String, url: String, context: Context, widthPixels: Int){
    val file = File(context.filesDir, filename)
//    download file if it doesn't exist
    if (!file.exists()) {
        val urlModified = url.plus("&fm=jpg&crop=entropy&cs=tinysrgb&w=")
            .plus(widthPixels.toString())
        try {
            val input = URL(urlModified).openStream().buffered().copyTo(file.outputStream())
            Timber.i("Downloading ".plus(urlModified).plus(" to ").plus(file.absolutePath))
        } catch (error: Throwable){
            Timber.e(error)
        }
    }
}
/*
Testing Utils
*/

// Introducing lag
fun sleep(){
    try {
        Thread.sleep(5000)
    } catch (e: InterruptedException){
        Timber.e(e)

    }
}

fun getActivity(context: Context) :Activity? {
    var mContext = context
    while (mContext is ContextWrapper) {
        if (mContext is Activity) {
            return mContext
        }
        mContext = mContext.baseContext
    }
    return null
}