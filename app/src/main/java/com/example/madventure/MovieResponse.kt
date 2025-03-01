package com.example.madventure

data class MovieResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)