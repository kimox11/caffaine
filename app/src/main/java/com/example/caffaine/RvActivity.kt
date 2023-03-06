package com.example.caffaine

import CustomAdapter
import MarsPhotosAdapter
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import network.MarsApi
import network.MarsPhoto
import java.io.IOException

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
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)
        recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        choosen_res_card = findViewById(R.id.choosedRes_card)
        choosen_res_img = findViewById(R.id.choosedRes_img)
        choosen_res_title = findViewById(R.id.choosedRes_title)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        var marsUiState: MarsUiState = MarsUiState.Loading
        choosen_res_title.setText(marsUiState.toString())
        choosen_res_title.setText(".....................")
        val app = applicationContext
        var listResult : List<MarsPhoto> = listOf(MarsPhoto("5","2"))
        runOnUiThread(Runnable() {
            // Stuff that updates the UI
            GlobalScope.launch{
                choosen_res_title.setText("..............")
                try {
                    listResult = MarsApi.retrofitService.getPhotos()

                    Log.i("YlaB2a", listResult.size.toString())

                    returned(listResult)
                    //MarsUiState.Success("Success: ${listResult.size} Mars photos retrieved")

                } catch (e: IOException) {
                    //choosen_res_title.setText(marsUiState.toString())
                    MarsUiState.Error

                }

            }

        });

        adapter = MarsPhotosAdapter(listOf(MarsPhoto("4","2")))
        recyclerview.adapter = adapter
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

