package com.example.caffaine

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvActivity : AppCompatActivity() {
    companion object{
        val res_menu = ArrayList<ItemsViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel


        res_menu.add(
            ItemsViewModel(
                R.drawable.mac,
                "Mac",
                "Ho ho ho ho ho ho ho Ho ho ho ho ho ho ho Ho ho ho ho ho ho ho\n" +
                        "Ho ho ho ho ho ho ho\n" +
                        "Ho ho ho ho ho ho ho"
            ))
        res_menu.add(
            ItemsViewModel(
                R.drawable.mac,
                "BOLO",
                "Hsdfsdfsdf\n" +
                        "gfhgfjgf\n" +
                        "Ho ho ho ho ho ho ho"
            ))
        res_menu.add(
                ItemsViewModel(
                    R.drawable.mac,
                    "rtyy",
                    "Ho hsdfdsfsdfho ho ho ho ho ho"

                ))
        res_menu.add(
            ItemsViewModel(
                R.drawable.mac,
                "uuuu",
                "Ho ho ho ho ho ho ho"
            ))
        res_menu.add(
                ItemsViewModel(
                    R.drawable.mac,
                    "hardes",
                    "chesseeeee"

                ))
        res_menu.add(
            ItemsViewModel(
                R.drawable.mac,
                "heart attack",
                "attaccckkkk"
            ))
        res_menu.add(
                ItemsViewModel(
                    R.drawable.mac,
                    "M",
                    "Ho ho ho Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
        res_menu.add(
            ItemsViewModel(
                R.drawable.mac,
                "KK",
                "o ho ho ho\n" +
                        "Ho ho ho ho ho ho ho\n" +
                        "Ho ho ho ho ho ho ho"
            ))
        res_menu.add(
                ItemsViewModel(
                    R.drawable.mac,
                    "7amza",
                    "Hsdfsdfsdfo ho\n" +
                            "Ho ho ho ho ho ho ho\n" +
                            "Ho ho ho ho ho ho ho"
                ))
        res_menu.add(
            ItemsViewModel(
                R.drawable.mac,
                "KFS",
                "o ho ho ho ho Ho ho ho ho ho ho ho\n" +
                        "fsdfsdfsdf\n" +
                        " ho ho ho"
            ))

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(res_menu)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}