package com.example.catviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CatViewerApi {
    @GET("v1/images/search")
    suspend fun getRandomCat(): retrofit2.Response<List<getRandomCatResponse>>
}

fun createCatViewerApi(): CatViewerApi {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/") // Replace with the actual API URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(CatViewerApi::class.java)
}