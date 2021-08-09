package com.ch.movie.ui.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.api.Repository
import com.ch.movie.model.Movie
import com.ch.movie.model.Movies
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieListViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    val movieList : MutableLiveData<Array<Movie>> = MutableLiveData() //using val to protect instead of using getterMethod


    fun getPopularList(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getPopularMovieList()
            movieList.value = response.body()?.results
        }
    }

    fun getTopRatedList(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getTopRatedMovieList()
            movieList.value = response.body()?.results
        }
    }


    fun getPopularListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getPopularMovieListNextPage()
            movieList.value = response.body()?.results
        }
    }

    fun getTopRatedListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getTopRatedMovieListNextPage()
            movieList.value = response.body()?.results
        }
    }
}