package com.ch.movie.db;

import androidx.lifecycle.LiveData
import androidx.room.*

import com.ch.movie.model.Movie;

@Dao
interface WatchListDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movie_list")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie_list WHERE id= :id")
    fun  isMovieAdded(id: Int): LiveData<List<Movie>>

}
