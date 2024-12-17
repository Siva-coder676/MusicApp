package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("x-rapidapi-key: fcb54aa690msh0459d8c24fef2d6p1785e0jsn0e974affe092" , "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getdata(@Query("q") query:String):Call<MyData>
}