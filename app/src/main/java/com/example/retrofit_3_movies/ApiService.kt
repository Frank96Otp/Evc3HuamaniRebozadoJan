package com.example.retrofit_3_movies

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getListPopularMovie(@Query("api_key") api_key:String, @Query("page") page:Int = 1): Response<MovieModel>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovie(@Path("movie_id") movie_id:Int, @Query("api_key") api_key:String): Response<MovilDetailModel>
}