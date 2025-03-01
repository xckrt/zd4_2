package com.example.madventure

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    fun getMoviesBySearchQuery(
        @Query("s") query: String,
        @Query("apikey") apiKey: String
    ): Call<MovieResponse>
}
