package com.ch.movie.db

import android.content.Context
import androidx.lifecycle.Transformations
import com.ch.movie.model.Movie
import com.ch.movie.model.TvShow
import javax.inject.Inject

class Repository @Inject constructor(var watchListDao:WatchListDao) {

    suspend fun insert(movie: Movie) = watchListDao.insert(movie)
    suspend fun update(movie: Movie) = watchListDao.update(movie)
    suspend fun delete(movie: Movie) = watchListDao.delete(movie)
    fun getAllMovieWatchList() =  watchListDao.getAllMovies()
    fun getIsMovieAdded(id: Int) = Transformations.map( watchListDao.isMovieAdded(id)) {
        list -> list.isNotEmpty()
    }

    suspend fun insert(tvShow: TvShow) = watchListDao.insert(tvShow)
    suspend fun update(tvShow: TvShow) = watchListDao.update(tvShow)
    suspend fun delete(tvShow: TvShow) = watchListDao.delete(tvShow)
    fun getAllTvShowWatchList() =  watchListDao.getAllTvShows()
    fun getIsTvShowAdded(id: Int) = Transformations.map( watchListDao.isTvShowAdded(id)) {
            list -> list.isNotEmpty()
    }

}