package com.example.retrofit_3_movies

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

   private  val retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service =  retrofit.create(ApiService::class.java)

}