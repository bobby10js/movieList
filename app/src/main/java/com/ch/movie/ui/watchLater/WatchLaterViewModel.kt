package com.ch.movie.ui.watchLater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.db.Repository
import com.ch.movie.model.Movie
import kotlinx.coroutines.launch

class WatchLaterViewModel(private var watchListRepo: Repository) :ViewModel() {
    private val watchLater: MutableLiveData<Array<Movie>> = MutableLiveData()

    fun addToWatchLater(movie:Movie){
        viewModelScope.launch {
            watchListRepo.insert(movie)
        }
    }

    fun setAllWatchLater(){
        viewModelScope.launch {
            watchLater.postValue(watchListRepo.getAllWatchList().toTypedArray())
        }
    }

    fun removeFromWatchLater(movie: Movie){
        viewModelScope.launch {
            watchListRepo.delete(movie)
        }
    }


//    fun checkMovieIsAdded(){
//        viewModelScope.launch {
//            isMovieAdded.postValue(watchListRepo.isMovieAdded(id).isNotEmpty())
//        }
//    }
    fun getAllWatchList(): LiveData<Array<Movie>> {
        return watchLater
    }
    fun getIsMovieAdded(id:Int): LiveData<List<Movie>> {
        return watchListRepo.getIsMovieAdded(id)
    }




}