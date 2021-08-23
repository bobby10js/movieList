package com.ch.movie.ui.watchLater

import androidx.lifecycle.*
import com.ch.movie.db.Repository
import com.ch.movie.model.Movie
import com.ch.movie.model.TvShow
import com.google.android.material.slider.Slider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WatchLaterViewModel  @Inject constructor( private var watchListRepo: Repository) :ViewModel() {


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