package com.example.caffaine

import android.R
import android.R.attr.path
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class DownloadPhotoFromUrl (): Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(Runnable {
            try {
                var path:String = intent?.getStringExtra("url").toString()
                var `in`: InputStream? = null
                var bmp: Bitmap? = null
                var responseCode = -1
                val url = URL(path) //"http://192.xx.xx.xx/mypath/img1.jpg
                val con: HttpURLConnection = url.openConnection() as HttpURLConnection
                con.setDoInput(true)
                con.connect()
                responseCode = con.getResponseCode()
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    //download
                    `in` = con.getInputStream()
                    bmp = BitmapFactory.decodeStream(`in`)
                    `in`.close()
                    var intentResult: Intent = Intent(userManager.PHOTO_BROADCAST)
                    intentResult.putExtra("img", bmp)
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentResult)
                }
            } catch (ex: Exception) {

            }
        }).start()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

}