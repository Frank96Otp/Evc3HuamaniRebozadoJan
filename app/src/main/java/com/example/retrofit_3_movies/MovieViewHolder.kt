package com.example.retrofit_3_movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_3_movies.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun render(movie: DetailsMovie, getId: (Int) -> Unit){

        binding.containerItem.setOnClickListener { getId(movie.id) }

        Picasso.get().load("https://image.tmdb.org/t/p/w185/${movie.imagen}").into(binding.ivMovie)
        binding.tvMovie.text = movie.titulo
    }
}