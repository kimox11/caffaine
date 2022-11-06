package com.example.caffaine

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeScreen : AppCompatActivity() {
    lateinit var click_button:Button
    lateinit var click_bttonn :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        var mediaPlayer = MediaPlayer.create(this, R.raw.ss)
        click_button = findViewById(R.id.sound)
        click_bttonn = findViewById(R.id.stopp)

        click_button.setOnClickListener(){
            startService(Intent(this,MyService::class.java))
        }

        click_bttonn.setOnClickListener(){
            stopService(Intent(this,MyService::class.java))
        }
//        click_button.setOnClickListener(){
//            if(!mediaPlayer.isPlaying){
//                mediaPlayer.start()
//            }
//             // no need to call prepare(); create() does that for you
//
//        }
    }
}