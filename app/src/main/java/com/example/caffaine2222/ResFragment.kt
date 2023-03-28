package com.example.caffaine2222

import CustomAdapter
import MarsPhotosAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import network.MarsPhoto


class ResFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataManager.setData()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_rv, container,
            false)
        val activity = activity as Context
        if(view.findViewById<RecyclerView>(R.id.recyclerview) != null){
            RvActivity.recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)
            RvActivity.adapter = MarsPhotosAdapter(listOf(MarsPhoto("5","2")))
            RvActivity.recyclerview = view.findViewById(R.id.recyclerview)
            RvActivity.recyclerview.layoutManager = LinearLayoutManager(activity)
            RvActivity.recyclerview.adapter = CustomAdapter(DataManager.list_of_restaurants)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun frag() = ResFragment()
    }
}