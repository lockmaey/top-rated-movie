package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.database.dao.MovieDao
import com.example.movies.database.entity.Movie
import kotlinx.coroutines.flow.Flow

class MovieViewModel(private val movieDao: MovieDao) : ViewModel() {

    fun allMovie(): Flow<List<Movie>> = movieDao.getAll()

    fun filterByTitleOrYear(search: String): Flow<List<Movie>> {
        if (search.isEmpty() || search.isEmpty())
            return movieDao.getAll()
        println("Search Data: $search")
        return movieDao.filterByTitleOrDate(search)
    }

    fun insert(movies: MutableList<Movie>) {
        movieDao.insert(movies)
    }
}

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val movieDao: MovieDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(movieDao) as T
        }
        throw IllegalArgumentException("Wrong viewmodel")
    }
}