package com.ch.movie.ui.watchLater

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.db.Repository
import com.ch.movie.model.Movie
import kotlinx.coroutines.launch

class WatchLaterViewModel(private var watchListRepo: Repository) :ViewModel() {
//    private val watchLater: MutableLiveData<Array<Movie>> = MutableLiveData()

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

//    fun setAllWatchLater(){
//        viewModelScope.launch {
//            watchLater.postValue(watchListRepo.getAllWatchList().toTypedArray())
//        }
//    }



    fun getAllWatchList(): LiveData<List<Movie>> {
        return watchListRepo.getAllWatchList()
    }



}