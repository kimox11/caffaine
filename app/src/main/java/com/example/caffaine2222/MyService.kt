package com.example.caffaine2222

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {

    private lateinit var mediaplayer : MediaPlayer
    override fun onCreate() {
        super.onCreate()
        mediaplayer = MediaPlayer.create(this , R.raw.ss)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaplayer.setLooping(true)
        mediaplayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaplayer.stop()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}