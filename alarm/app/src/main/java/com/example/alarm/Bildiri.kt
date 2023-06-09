package com.example.alarm

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Bildiri(context: Context?) {
    private val channelID="456"
    fun createNotification(context: Context, message: String, number:Int){
        val notification = NotificationCompat.Builder(context, channelID)
            .setContentTitle(message.split(".")[0] + " ⌛")
            .setContentText(message.split(".")[1])
            .setSmallIcon(R.drawable.ic_notify)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(0, notification)
    }

}