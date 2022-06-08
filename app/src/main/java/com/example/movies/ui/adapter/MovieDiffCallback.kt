package com.example.movies.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movies.database.entity.Movie

class MovieDiffCallback(
    private val oldMovies: List<Movie>,
    private val newMovies: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldMovies.size

    override fun getNewListSize() = newMovies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition].id == newMovies[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldMovies[oldItemPosition];
        val newMovie = newMovies[newItemPosition];

        return oldMovie.title == newMovie.title;
    }
}