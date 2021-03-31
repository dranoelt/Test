package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color.argb
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val channelId = "myApps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val texts = binding.editTextTextPersonName.text
        val email = binding.editTextTextPersonName2.text
        val umur = binding.editTextTextPersonName3.text
        val btn = binding.button

        createNotificationChannel(channelId)
        btn.setOnClickListener {

            val d = Profil (texts.toString(), email.toString(), umur.toString())
            val intentAct  = Intent(this, MainActivity2::class.java)
            intentAct.putExtra(EXTRA_KEY,d)
            val pendingIntent = PendingIntent.getActivity(this, 0, intentAct, PendingIntent.FLAG_UPDATE_CURRENT)
//            val contentView = RemoteViews(packageName, R.layout.activity_main2)

            val notificationBuilder = NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Title: API LEVEL " + Build.VERSION.SDK_INT)
                .setContentText("UUID: " + UUID.randomUUID())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColor(argb(1,28,255,245))
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)

            notificationBuilder.setStyle(NotificationCompat.MessagingStyle("Henlo")
                .addMessage("Hi, who are you?",System.currentTimeMillis()-30000,"Henlo")
                .addMessage("Hi, I'm Honky",System.currentTimeMillis(),"Honky")
                .setGroupConversation(true)
            )

            with(NotificationManagerCompat.from(this)){
                notify(1, notificationBuilder.build())
            }
        }
    }

    private fun createNotificationChannel(channelId :String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = getString(R.string.channelName)
            val channelDescription = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }


            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}