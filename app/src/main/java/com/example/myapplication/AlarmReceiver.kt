package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat


const val EXTRA_PESAN: String = "EXTRA_PESAN"
class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val Notifyid = 10001
            val Channel_id = "channel_01"
            val name = "Reminder"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val nNotifyChannel = NotificationChannel(Channel_id, name, importance)
            val mBuilder = NotificationCompat.Builder(context!!, Channel_id)
                    .setSmallIcon(R.drawable.ic_notif)
                    .setContentText(intent?.getStringExtra(EXTRA_PESAN))
                    .setContentTitle("Reminder")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            var mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            for (s in mNotificationManager.notificationChannels) {
                mNotificationManager.deleteNotificationChannel(s.id)
            }
            mNotificationManager.createNotificationChannel(nNotifyChannel)
            mNotificationManager.notify(Notifyid,mBuilder.build())
        }
    }
}