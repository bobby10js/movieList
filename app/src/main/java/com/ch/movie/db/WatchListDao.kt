package com.ch.movie.db;

import androidx.lifecycle.LiveData
import androidx.room.*

import com.ch.movie.model.Movie;
import com.ch.movie.model.TvShow

@Dao
interface WatchListDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movie_list")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_list WHERE id= :id")
    fun  isMovieAdded(id: Int): LiveData<List<Movie>>


    @Insert
    suspend fun insert(tvShow: TvShow )

    @Update
    suspend fun update(tvShow: TvShow)

    @Delete
    suspend fun delete(tvShow: TvShow)

    @Query("SELECT * FROM tv_show_list")
    fun getAllTvShows(): LiveData<List<TvShow>>

    @Query("SELECT * FROM tv_show_list WHERE id= :id")
    fun  isTvShowAdded(id: Int): LiveData<List<TvShow>>

}
