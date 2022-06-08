package com.example.movies.utils

import android.content.Context
import com.example.movies.database.entity.Movie
import com.example.movies.utils.loadJsonFile
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@OptIn(ExperimentalStdlibApi::class)
fun initializeData(context: Context): MutableList<Movie> {
    val json = loadJsonFile(context = context, fileName = "movies.json")

    val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter = moshi.adapter<List<Movie>>()

    return jsonAdapter.fromJson(json) as MutableList<Movie>
}