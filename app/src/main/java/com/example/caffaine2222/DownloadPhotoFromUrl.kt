package com.example.caffaine2222

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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
                val con: HttpURLConnection = url.openConnection() as HttpURLConnection // open connection
                con.setDoInput(true)
                con.connect()
                responseCode = con.getResponseCode()// get response after opening connection
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    //download
                    `in` = con.getInputStream()
                    bmp = BitmapFactory.decodeStream(`in`) // put pic here
                    `in`.close() // close connection
                    var intentResult: Intent = Intent(userManager.PHOTO_BROADCAST)
                    intentResult.putExtra("img", bmp)// put pic in this intent
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