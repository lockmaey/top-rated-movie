package com.example.movies

import android.app.Application
import com.example.movies.database.AppDatabase

class MovieApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}