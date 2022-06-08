package com.example.movies.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Movie(
    @PrimaryKey
    val id: Int,
    @NonNull @ColumnInfo(name = "title")
    val title: String,
    @NonNull @ColumnInfo(name = "release_date")
    val release_date: String,
    @NonNull @ColumnInfo(name = "poster_path")
    val poster_path: String
)
