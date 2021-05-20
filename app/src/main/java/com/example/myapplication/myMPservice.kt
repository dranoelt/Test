package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import kotlin.time.minutes

const val ACTION_PLAY = "PLAY"
const val ACTION_STOP = "STOP"
const val ACTION_CREATE = "CREATE"
const val ACTION_PAUSE = "PAUSE"
const val ACTION_START = "START"

class myMPservice : Service(),
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener {

    var myMediaPlayer : MediaPlayer? = null

    fun init (){
        myMediaPlayer = MediaPlayer()
        myMediaPlayer?.setOnPreparedListener(this)
        myMediaPlayer?.setOnCompletionListener(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            var actionIntent = intent.action
            when (actionIntent) {
                ACTION_CREATE -> init()
                ACTION_PLAY -> {
                    if (!myMediaPlayer!!.isPlaying) {
                        val assetFileDescriptor = this.resources
                            .openRawResourceFd(R.raw.lovepoem)
                        myMediaPlayer?.run {
                            reset()
                            setDataSource(
                                assetFileDescriptor.fileDescriptor,
                                assetFileDescriptor.startOffset,
                                assetFileDescriptor.declaredLength
                            )
                            prepareAsync()
                        }
                    }
                }
                ACTION_START -> myMediaPlayer?.start()
                ACTION_PAUSE -> myMediaPlayer?.pause()
                ACTION_STOP -> {
                    myMediaPlayer?.stop()
                    Toast.makeText(this, "Player Stop", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return flags
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return false
    }

    override fun onCompletion(mp: MediaPlayer?) {

    }

    override fun onPrepared(mp: MediaPlayer?) {
        myMediaPlayer?.start()
    }

    override fun onDestroy() {
        myMediaPlayer?.release()
    }
}