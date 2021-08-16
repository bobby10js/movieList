package com.ch.movie.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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
    fun getAllWatchList() =  watchListDao.getAllMovies()
    fun getIsMovieAdded(id: Int) = Transformations.map( watchListDao.isMovieAdded(id)) {
        list -> list.isNotEmpty()
    }


}