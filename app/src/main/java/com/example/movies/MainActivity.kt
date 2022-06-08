package com.example.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.ui.adapter.MovieAdapter
import com.example.movies.utils.initializeData
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.MovieViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieViewModel: MovieViewModel by viewModels {
            MovieViewModelFactory(
                (application as MovieApplication).database.movieDao()
            )
        }

        setContentView(R.layout.activity_main)

        val movieRv = findViewById<RecyclerView>(R.id.moviesRV)
        val searchLayout = findViewById<TextInputLayout>(R.id.searchTextLayout)
        val searchText = findViewById<TextInputEditText>(R.id.searchText)

        val moviesList = initializeData(context = applicationContext)

        val movieAdapter = MovieAdapter(
            movieList = mutableListOf()
        )

        lifecycle.coroutineScope.launch(Dispatchers.IO) {
            movieViewModel.insert(moviesList)
            movieViewModel.allMovie().collect {
                movieAdapter.updateData(it)
            }
        }

        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        movieRv.layoutManager = layoutManager
        movieRv.adapter = movieAdapter

        searchLayout.setEndIconOnClickListener {
            lifecycle.coroutineScope.launch {
                movieViewModel.filterByTitleOrYear(searchText.text.toString()).collect{
                    movieAdapter.updateData(it)
                }
            }
        }
    }
}