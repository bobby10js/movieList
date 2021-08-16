package com.ch.movie.ui.watchLater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.db.Repository
import com.ch.movie.model.Movie
import com.ch.movie.model.TvShow
import com.google.android.material.slider.Slider
import kotlinx.coroutines.launch

class WatchLaterViewModel(private var watchListRepo: Repository) :ViewModel() {


    fun addToWatchLater(movie:Movie){
        viewModelScope.launch {
            watchListRepo.insert(movie)
        }
    }


    fun getIsMovieAdded(id:Int): LiveData<Boolean> {
        return watchListRepo.getIsMovieAdded(id)
    }


    fun removeFromWatchLater(movie: Movie){
        viewModelScope.launch {
            watchListRepo.delete(movie)
        }
    }

    fun addToWatchLater(tvShow: TvShow) {
        viewModelScope.launch {
            watchListRepo.insert(tvShow)
        }
    }

    fun getIsTvShowAdded(id:Int): LiveData<Boolean> {
        return watchListRepo.getIsTvShowAdded(id)
    }

    fun removeFromWatchLater(tvShow: TvShow) {
        viewModelScope.launch {
            watchListRepo.delete(tvShow)
        }
    }

    fun getAllMovieWatchList(): LiveData<List<Movie>> {
        return watchListRepo.getAllMovieWatchList()
    }

    fun getAllTvShowWatchList(): LiveData<List<TvShow>> {
        return watchListRepo.getAllTvShowWatchList()
    }


}