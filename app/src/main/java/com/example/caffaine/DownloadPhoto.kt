package com.example.caffaine

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class DownloadPhoto : AppCompatActivity() {
    companion object {
        lateinit var img: ImageView
    }
    lateinit var download: Button
    lateinit var url_edit_text: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_photo)
        IntentFilter(userManager.PHOTO_BROADCAST).also {
            LocalBroadcastManager.getInstance(this).registerReceiver(photoReceiver(),it)
        }
        download = findViewById(R.id.btn_download)
        img = findViewById(R.id.image_download)
        url_edit_text = findViewById(R.id.url_download)



        download.setOnClickListener{
            var intent1 = Intent(this,DownloadPhotoFromUrl::class.java)
            intent1.putExtra(
                    "url",
                   url_edit_text.text.toString()
               )
            startService(intent1)
        }
    }

     fun setphoto(bmp: Bitmap?) {
         img.setImageBitmap(bmp)
    }


}