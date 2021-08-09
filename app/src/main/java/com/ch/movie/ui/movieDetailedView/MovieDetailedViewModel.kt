package com.ch.movie.ui.movieDetailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.api.Repository
import com.ch.movie.model.Casts
import com.ch.movie.model.Movie
import com.ch.movie.model.Movies
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailedViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    val movieDetails: MutableLiveData<Movie> = MutableLiveData()
    val similarMovieDetails: MutableLiveData<Movies> = MutableLiveData()
    val castDetails: MutableLiveData<Casts> = MutableLiveData()

    fun getDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<Movie> = repository.getMovieDetail(id)
            movieDetails.value = response.body()
        }
    }

    fun getSimilarMovieDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<Movies> = repository.getSimilarMovieDetail(id)
            similarMovieDetails.value = response.body()
        }
    }

    fun getCastDetails(id: Int) {
        viewModelScope.launch {
            val response: Response<Casts> = repository.getMovieCastDetails(id)
            castDetails.value = response.body()
        }
    }
}
