package com.example.myapplication


import android.content.Intent
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMain3Binding


class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding
    var myIntent : Intent? = null
    var mMediaPlayer : myMPservice ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3)

        myIntent = Intent(this, myMPservice::class.java)
        myIntent?.setAction(ACTION_CREATE)
        startService(myIntent)


        binding.playPlayer.setOnClickListener {
            if(binding.playPlayer.text.equals("Play")) {
                binding.playPlayer.setText("Pause")
                myIntent?.setAction(ACTION_PLAY)
                startService(myIntent)
            }
            else if (binding.playPlayer.text.equals("Resume")) {
                binding.playPlayer.setText("Pause")
                myIntent?.setAction(ACTION_START)
                startService(myIntent)
            }
            else {
                binding.playPlayer.setText("Resume")
                myIntent?.setAction(ACTION_PAUSE)
                startService(myIntent)
            }
        }
        binding.stopPlayer.setOnClickListener {
            binding.playPlayer.setText("Play")
            myIntent!!.setAction(ACTION_STOP)
            startService(myIntent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(myIntent)
    }
}