package com.example.caffaine

import CustomAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RvActivity : AppCompatActivity() {
    companion object{
        var switch = 0
        lateinit var adapter: CustomAdapter
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

        // ArrayList of class ItemsViewModel
        //set Data
        DataManager.setData()

        // This will pass the ArrayList to our Adapter
        adapter = CustomAdapter(DataManager.list_of_restaurants)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    override fun onBackPressed() {
        if(switch != 0){
            switch = 0
            choosen_res_card.visibility = View.GONE
            adapter.setData(DataManager.list_of_restaurants)
        }else{
            super.onBackPressed()
        }
    }
}

