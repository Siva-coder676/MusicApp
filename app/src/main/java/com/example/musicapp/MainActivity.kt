package com.example.musicapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val retrofitBuilder =
            Retrofit.Builder().baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getdata("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {


            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // Uncomment for actual usage
                val dataList = response.body()?.data!!



              myAdapter= MyAdapter(this@MainActivity,dataList)
               myRecyclerView.adapter=myAdapter
               myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                // val textView = findViewById<TextView>(R.id.helloText)
                // textView.text = dataList.toString()
                // Log.d("Tag:onResponse", "onResponse: ${response.body()}")
            }


            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("Tag:onFailure", "onFailure: ${t.message}")
            }
        })
    }
}
