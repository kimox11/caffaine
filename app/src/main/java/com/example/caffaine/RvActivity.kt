package com.example.caffaine

import CustomAdapter
import MarsPhotosAdapter
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import network.ApiService
import network.MarsApiService
import network.MarsPhoto
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.lang.Runnable
import javax.security.auth.callback.Callback

enum class MarsUiState {
    Success,Error,Loading,
}

interface status{
    fun returned(listResult: List<MarsPhoto>)
}

class RvActivity : AppCompatActivity(), status {
    companion object{
        var switch = 0
        lateinit var adapter: MarsPhotosAdapter
        lateinit var recyclerview:RecyclerView
        lateinit var choosen_res_card: LinearLayout
        lateinit var choosen_res_title:TextView
        lateinit var choosen_res_img:ImageView
        lateinit var loading:ImageView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)
        recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        choosen_res_card = findViewById(R.id.choosedRes_card)
        choosen_res_img = findViewById(R.id.choosedRes_img)
        choosen_res_title = findViewById(R.id.choosedRes_title)
        loading = findViewById(R.id.loading)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        var marsUiState: MarsUiState = MarsUiState.Loading
        choosen_res_title.setText(marsUiState.toString())
        choosen_res_title.setText(".....................")
        val app = applicationContext
        var listResult : List<MarsPhoto> = listOf(MarsPhoto("5","2"))

        val serviceGenerator = MarsApiService.buildService(ApiService::class.java)
        val call = serviceGenerator.getPhotos()
        if(marsUiState == MarsUiState.Loading){
            loading.visibility = View.VISIBLE
            choosen_res_card.visibility = View.GONE
            recyclerview.visibility = View.GONE
        }
        call.enqueue(object : retrofit2.Callback<MutableList<MarsPhoto>> {
            override fun onResponse(
                call: Call<MutableList<MarsPhoto>>,
                response: Response<MutableList<MarsPhoto>>
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val name = "hi"
                    val descriptionText = "hi"
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel("115492", name, importance).apply {
                        description = descriptionText
                    }
                    // Register the channel with the system
                    val notificationManager: NotificationManager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(channel)
                }
                recyclerview.apply {
                    adapter = MarsPhotosAdapter(response.body()!!)
                    loading.visibility = View.GONE
                    visibility = View.VISIBLE
                }

            }

            override fun onFailure(call: Call<MutableList<MarsPhoto>>, t: Throwable) {

            }

        })
        // ArrayList of class ItemsViewModel
        //set Data
        DataManager.setData()

        // This will pass the ArrayList to our Adapter
        //adapter = CustomAdapter(DataManager.list_of_restaurants)
        // Setting the Adapter with the recyclerview
        //recyclerview.adapter = adapter
        //choosen_res_title.setText(marsUiState.toString())

    }




    override fun onBackPressed() {
        if(switch != 0){
            switch = 0
            choosen_res_card.visibility = View.GONE
            //adapter.setData(DataManager.list_of_restaurants)
        }else{
            super.onBackPressed()
        }
    }

    override fun returned(listResult: List<MarsPhoto>) {
        adapter.setData(listResult)
    }
}

