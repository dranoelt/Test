package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.argb
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val channelId = "myApps"
    var notificationManager :NotificationManager? = null
    var myNotif: NotificationCompat.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val texts = binding.editTextTextPersonName.text
        val email = binding.editTextTextPersonName2.text
        val umur = binding.editTextTextPersonName3.text
        val btn = binding.button

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel()


        btn.setOnClickListener {

            val d = Profil (texts.toString(), email.toString(), umur.toString())
            val intentAct  = Intent(this, MainActivity2::class.java)
            intentAct.putExtra(EXTRA_KEY,d)
            val pendingIntent = PendingIntent.getActivity(this, 0, intentAct, PendingIntent.FLAG_UPDATE_CURRENT)

            myNotif = NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Title: API LEVEL " + Build.VERSION.SDK_INT)
                .setContentText("UUID: " + UUID.randomUUID())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColor(argb(1,28,255,245))
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setNumber(2)

                .setStyle(NotificationCompat.MessagingStyle(texts.toString())
                    .addMessage("Hi, who are you?",System.currentTimeMillis()-30000,texts.toString())
                    .addMessage("Hi, I'm Honky",System.currentTimeMillis(),"Honky")
                    .setGroupConversation(true)
                )
            notificationManager?.notify(1,myNotif?.build())
        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationSound = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val att = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
            val channelName = getString(R.string.channelName)
            val channelDescription = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            channel.vibrationPattern =
                    longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
            channel.setSound(notificationSound,att)
            channel.lightColor = Color.RED
            channel.enableLights(true)
            channel.enableVibration(true)
            notificationManager?.createNotificationChannel(channel)
        }
    }
}