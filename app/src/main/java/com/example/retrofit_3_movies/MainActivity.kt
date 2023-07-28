package com.example.retrofit_3_movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_3_movies.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //1c6ae287ba9b3e663159acb8a1e49b9b

    companion object{
        const val API_KEY = "1c6ae287ba9b3e663159acb8a1e49b9b"
        const val EXTRA_KEY = "key"
    }
    private var numPage = 1
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private  var llManger = GridLayoutManager(this,2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRetrofit()
        initRecyclerview()
        initListener()
    }

    private fun initListener() {
        binding.btnNextListMovie.setOnClickListener {

            numPage++
            updateListMovie(numPage)
          //  llManger.scrollToPosition(0)
            binding.btnNextListMovie.visibility = View.GONE
            binding.btnNextMovie.visibility = View.VISIBLE
            binding.btnbackMovie.visibility = View.VISIBLE
            Log.i("frank" , "pagina: ${numPage}")
        }
        binding.btnNextMovie.setOnClickListener {


            numPage++
            updateListMovie(numPage)
         //   llManger.scrollToPosition(0)
            Log.i("frank" , "pagina: ${numPage}")

        }
        binding.btnbackMovie.setOnClickListener {

            numPage--
            updateListMovie(numPage)
         //   llManger.scrollToPosition(0)
            if(numPage==1){
                binding.btnNextListMovie.visibility = View.VISIBLE
                binding.btnNextMovie.visibility = View.GONE
                binding.btnbackMovie.visibility = View.GONE
            }
            Log.i("frank" , "pagina: ${numPage}")
        }
    }

    private fun initRecyclerview() {

        adapter = MovieAdapter(){navegateToDetailMovie(it)}
        binding.rvMovies.layoutManager = llManger
        binding.rvMovies.adapter = adapter
       // binding.rvMovies.layoutManager = llManger
    }

    private fun initRetrofit() {
        updateListMovie(numPage)
    }

    private fun updateListMovie( pag: Int){
        CoroutineScope(Dispatchers.IO).launch {

            var response = RetrofitObject.service.getListPopularMovie(API_KEY, pag)
            if(response.isSuccessful){

                var body = response.body()

                if(body != null){

                    runOnUiThread {


                        adapter.listMovies = body.results
                        adapter.notifyDataSetChanged()
                        llManger.scrollToPosition(0)
                        Log.i("frank", body.results.toString())
                    }
                }
            }
        }

    }

    private fun navegateToDetailMovie(id:Int){
        Log.i("idMovie", "id: ${id}")
        val intent = Intent(this, DetailsMovieActivity::class.java)
        intent.putExtra(EXTRA_KEY, id.toString())

        startActivity(intent)
    }
}