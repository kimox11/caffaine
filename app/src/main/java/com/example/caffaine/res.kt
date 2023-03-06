package com.example.caffaine

import CustomAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class res : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res)
        var f = Fragment(R.layout.activity_rv)//change
        val fram = supportFragmentManager.beginTransaction()
        fram.replace(R.id.fragmentContainerView, f)
        fram.commit()
    }
}