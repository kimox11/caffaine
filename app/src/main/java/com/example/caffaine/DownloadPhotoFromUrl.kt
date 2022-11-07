package com.example.caffaine

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.os.IBinder
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.File

class DownloadPhotoFromUrl (): Service() {

    var msg: String? = ""
    var lastMsg = ""



    @SuppressLint("Range")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var url:String = intent?.getStringExtra("url").toString()
        val directory = File(Environment.DIRECTORY_PICTURES)

        if (!directory.exists()) {
            directory.mkdirs()
        }
        val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadUri = Uri.parse(url)

        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(url.substring(url.lastIndexOf("/") + 1))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    url.substring(url.lastIndexOf("/") + 1)
                )
        }

        val downloadId = downloadManager.enqueue(request)
        val query = DownloadManager.Query().setFilterById(downloadId)
        Thread(Runnable {
            var downloading = true
            while (downloading) {
                val cursor: Cursor = downloadManager.query(query)
                cursor.moveToFirst()
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                msg = statusMessage(url, directory, status)
                if (msg != lastMsg) {
                        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    lastMsg = msg ?: ""
                }
                if(DownloadManager.STATUS_SUCCESSFUL == status){

                    userManager.directory = Environment.DIRECTORY_PICTURES +"/"+ url.substring(url.lastIndexOf("/") + 1)
                    println(userManager.directory)
                    LocalBroadcastManager.getInstance(this)
                        .sendBroadcast(Intent(userManager.PHOTO_BROADCAST))
                }
                cursor.close()
            }
        }).start()

        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Image downloaded successfully in $directory" + File.separator + url.substring(
                url.lastIndexOf("/") + 1
            )
            else -> "There's nothing to download"
        }
        return msg
    }
}