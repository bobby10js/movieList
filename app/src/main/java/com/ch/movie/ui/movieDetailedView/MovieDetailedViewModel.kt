package com.ch.movie.ui.movieDetailedView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.api.Repository
import com.ch.movie.model.Casts
import com.ch.movie.model.Movie
import com.ch.movie.model.Movies
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailedViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val movieDetails: MutableLiveData<Movie> = MutableLiveData()
    private val similarMovieDetails: MutableLiveData<Movies> = MutableLiveData()
    private val castDetails: MutableLiveData<Casts> = MutableLiveData()


    fun getMovieDetails() : LiveData<Movie>{
        return movieDetails
    }

    fun getSimilarMovieDetails() : LiveData<Movies>{
        return similarMovieDetails
    }

    fun getCastDetails() : LiveData<Casts>{
        return castDetails
    }

    fun setDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<Movie> = repository.getMovieDetail(id)
            movieDetails.value = response.body()
        }
    }

    fun setSimilarMovieDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<Movies> = repository.getSimilarMovieDetail(id)
            similarMovieDetails.value = response.body()
        }
    }

    fun setCastDetails(id: Int) {
        viewModelScope.launch {
            val response: Response<Casts> = repository.getMovieCastDetails(id)
            castDetails.value = response.body()
        }

    }




}
