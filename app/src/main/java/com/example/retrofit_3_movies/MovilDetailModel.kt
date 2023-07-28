package com.example.retrofit_3_movies

import com.google.gson.annotations.SerializedName

data class MovilDetailModel(
    @SerializedName("genres") val genres: List<Generos>,
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val imagen:String,
    @SerializedName("overview") val synopsis: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("original_title") val title:String
)

data class Generos(@SerializedName("name") var name: String)
