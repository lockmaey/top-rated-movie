package com.example.movies.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.database.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT id, title, release_date, poster_path FROM movie ORDER BY title ASC")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT id, title, release_date, poster_path FROM movie WHERE title LIKE '%' || :search || '%' OR release_date = :search ORDER BY title ASC")
    fun filterByTitleOrDate(search: String): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)
}