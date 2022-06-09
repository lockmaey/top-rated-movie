package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.database.dao.MovieDao
import com.example.movies.database.entity.Movie
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 6

class MovieViewModel(private val movieDao: MovieDao) : ViewModel() {


    val movies = Pager(
        PagingConfig(
            pageSize = 1,
            enablePlaceholders = false,
            maxSize = 20
        )
    ) {
        movieDao.getAll()
    }.flow.cachedIn(viewModelScope)

    //fun allMovie(itemPerPage: Int): Flow<List<Movie>> = movieDao.getAll()

    fun filterByTitleOrYear(search: String): Flow<PagingData<Movie>> {
        if (search.isEmpty() || search.isEmpty())
            return movies
        return Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
                maxSize = 20
            )
        ) {
            movieDao.filterByTitleOrDate(search)
        }.flow.cachedIn(viewModelScope)
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