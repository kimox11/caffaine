package com.example.caffaine

import android.R.attr.path
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File


class DownloadPhoto : AppCompatActivity() {
    lateinit var download: Button
    lateinit var img: ImageView
    lateinit var url_edit_text: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_photo)
        registerReceiver(photoReceiver(), IntentFilter(userManager.PHOTO_BROADCAST));
        download = findViewById(R.id.btn_download)
        img = findViewById(R.id.image_download)
        url_edit_text = findViewById(R.id.url_download)


        val bitmap = BitmapFactory.decodeFile(userManager.directory)
        img.setImageBitmap(bitmap)
        download.setOnClickListener{
            intent = Intent(this,DownloadPhotoFromUrl::class.java)
            intent.putExtra(
                    "url",
                    url_edit_text.text.toString()
                )
            startService(intent)
        }
    }

     fun setphoto(){
        println("akakakakakakakakakakakakkakakakaka")
    }



}