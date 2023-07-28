package com.example.retrofit_3_movies


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit_3_movies.MainActivity.Companion.API_KEY
import com.example.retrofit_3_movies.MainActivity.Companion.EXTRA_KEY
import com.example.retrofit_3_movies.databinding.ActivityDetailsMovieBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsMovieActivity : AppCompatActivity() {

   private lateinit var binding:ActivityDetailsMovieBinding
   lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra(EXTRA_KEY).orEmpty()
        Log.i("id", "${id}")
        initServiceApi()

    }

    private fun initServiceApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<MovilDetailModel> =  RetrofitObject.service.getDetailsMovie(id.toInt(), API_KEY)

            if(response.isSuccessful){
                val body: MovilDetailModel? = response.body()
                if (body != null){
                    runOnUiThread {
                        initUI(body)
                    }
                }
            }
        }
    }

    private fun initUI(body: MovilDetailModel) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185/${body.imagen}").into(binding.ivMovieDetails)
        binding.tvSinopsis.text = body.synopsis
        binding.tvPopularity.text = body.popularity.toString()
        binding.tvTitleMovie.text = body.title
        var generos  = ""
        for (genre in body.genres) {

             generos += genre.name + ", "

        }
        binding.tvGeneros.text = generos
    }
}