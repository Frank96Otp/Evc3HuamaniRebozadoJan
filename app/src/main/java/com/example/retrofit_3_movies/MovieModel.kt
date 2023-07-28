package com.example.retrofit_3_movies

import com.google.gson.annotations.SerializedName

data class MovieModel(val results: List<DetailsMovie>)


data class DetailsMovie(
    val id: Int,
    @SerializedName("original_title")
    val titulo: String,
    @SerializedName("overview")
    val sinopsis: String,
    @SerializedName("poster_path")
    val imagen: String
)
