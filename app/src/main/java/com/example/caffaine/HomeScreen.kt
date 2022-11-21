package com.example.caffaine

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class HomeScreen : AppCompatActivity() {
    lateinit var click_button:Button
    lateinit var click_bttonn :Button
    lateinit var welcome_edit_text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        var mediaPlayer = MediaPlayer.create(this, R.raw.ss)
        click_button = findViewById(R.id.sound)
        click_bttonn = findViewById(R.id.stopp)
        welcome_edit_text = findViewById(R.id.name)

        click_button.setOnClickListener(){
            startService(Intent(this,MyService::class.java))
        }

        click_bttonn.setOnClickListener(){
            stopService(Intent(this,MyService::class.java))
        }
        welcome_edit_text.setText("Hello.. "+userManager.currentUser?.FirstName+" "+userManager.currentUser?.LastName + " !")
//        click_button.setOnClickListener(){
//            if(!mediaPlayer.isPlaying){
//                mediaPlayer.start()
//            }
//             // no need to call prepare(); create() does that for you
//
//        }
    }
}