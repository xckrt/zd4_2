package com.example.madventure

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChannelActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MovieAdapter
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)

        recyclerView = findViewById(R.id.movie_recycler_view)
        searchEditText = findViewById(R.id.searchEditText)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewAdapter = MovieAdapter(emptyList()) { movie ->
            showMovieDetails(movie)  // Показ деталей фильма
        }
        recyclerView.adapter = viewAdapter

        loadMovies("")  // Загрузка фильмов
        setupSearch()  // Настройка поиска
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener { text ->
            loadMovies(text.toString().trim())
        }
    }

    private fun loadMovies(query: String) {
        val apiService = RetrofitInstance.api
        apiService.getMoviesBySearchQuery(query, "ceca9cb5").enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.d("API Response", response.body().toString())
                val movies = response.body()?.Search ?: emptyList()
                viewAdapter.updateMovies(movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                viewAdapter.updateMovies(emptyList())
            }
        })
    }

    private fun showMovieDetails(movie: Movie) {
        val fragment = MovieDetailFragment.newInstance(movie)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}