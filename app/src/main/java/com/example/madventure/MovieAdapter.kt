package com.example.madventure

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private var movies: List<Movie>?, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(view.inflate(R.layout.movie_item, parent, false)) {
        private val titleView: TextView = itemView.findViewById(R.id.movie_title)
        private val imageView: ImageView = itemView.findViewById(R.id.movie_image)

        fun bind(movie: Movie) {
            titleView.text = movie.Title
            Glide.with(itemView.context).load(movie.Poster).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.let {
            val movie = it[position]
            holder.bind(movie)
            holder.itemView.setOnClickListener { onClick(movie) }
        }
    }

    override fun getItemCount(): Int = movies?.size ?: 0

    fun updateMovies(newMovies: List<Movie>?) {
        this.movies = newMovies ?: emptyList()
        notifyDataSetChanged()
    }
}
