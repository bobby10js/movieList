package com.ch.movie.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.ch.movie.model.Movie

class Repository(var context:Context) {
    private var watchListDao:WatchListDao
    init {
            val watchListDatabase = WatchListDatabase.getInstance(context)
            watchListDao = watchListDatabase.watchListDao()
        }


    suspend fun insert(movie: Movie) = watchListDao.insert(movie)
    suspend fun update(movie: Movie) = watchListDao.update(movie)
    suspend fun delete(movie: Movie) = watchListDao.delete(movie)
    suspend fun getAllWatchList():List<Movie> =  watchListDao.getAllMovies()

    fun getIsMovieAdded(id: Int) =  watchListDao.isMovieAdded(id)

}