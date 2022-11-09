package com.example.caffaine

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap

class photoReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
            var bmp: Bitmap? = intent.getParcelableExtra<Bitmap>("img")
            DownloadPhoto().setphoto(bmp)

    }
}