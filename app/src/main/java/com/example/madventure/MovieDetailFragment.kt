package com.example.madventure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class MovieDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        val title = arguments?.getString("title") ?: "Unknown"
        val year = arguments?.getString("year") ?: "N/A"
        val plot = arguments?.getString("plot") ?: "Description not available"
        val genre = arguments?.getString("genre") ?: "Genre not available"
        val language = arguments?.getString("language") ?: "Language not available"
        val country = arguments?.getString("country") ?: "Country not available"
        val posterUrl = arguments?.getString("poster") ?: ""

        view.findViewById<TextView>(R.id.movie_detail_title).text = title
        view.findViewById<TextView>(R.id.movie_detail_year).text = "Year: $year"
        view.findViewById<TextView>(R.id.movie_detail_plot).text = plot
        view.findViewById<TextView>(R.id.movie_detail_genre).text = "Genre: $genre"
        view.findViewById<TextView>(R.id.movie_detail_language).text = "Language: $language"
        view.findViewById<TextView>(R.id.movie_detail_country).text = "Country: $country"

        Glide.with(this).load(posterUrl).into(view.findViewById<ImageView>(R.id.movie_detail_image))

        return view
    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailFragment {
            val args = Bundle().apply {
                putString("title", movie.Title)
                putString("year", movie.Year)
                putString("plot", movie.Plot)
                putString("genre", movie.Genre)
                putString("language", movie.Language)
                putString("country", movie.Country)
                putString("poster", movie.Poster)
            }
            return MovieDetailFragment().apply {
                arguments = args
            }
        }

    }
}
