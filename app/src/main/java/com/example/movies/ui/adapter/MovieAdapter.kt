package com.example.movies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.R
import com.example.movies.database.entity.Movie


class MovieAdapter :
    PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(MOVIE_DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieList = getItem(position)
        holder.image.load(movieList?.poster_path)
        holder.title.text = movieList?.title
        holder.releaseDate.text = movieList?.release_date?.take(4)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.thumbnails)
        val title: TextView = itemView.findViewById(R.id.title)
        val releaseDate: TextView = itemView.findViewById(R.id.date_release)
    }

    companion object {
        private val MOVIE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(oldMovies: Movie, newMovies: Movie): Boolean =
                oldMovies.id == newMovies.id

            override fun areContentsTheSame(oldMovies: Movie, newMovies: Movie): Boolean =
                oldMovies == newMovies

        }
    }
}