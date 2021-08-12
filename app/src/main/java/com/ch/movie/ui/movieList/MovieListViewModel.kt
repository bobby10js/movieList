package com.ch.movie.ui.movieList

import androidx.lifecycle.LiveData
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

    private var movieList : MutableLiveData<Array<Movie>> = MutableLiveData() //using val to protect instead of using getterMethod

    fun getMovieList() : LiveData<Array<Movie>> {
        return movieList
    }

    fun getPopularList(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getPopularMovieList()
            movieList.postValue (response.body()?.results)
        }
    }

    fun getTopRatedList(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getTopRatedMovieList()
            movieList.postValue (response.body()?.results)
        }
    }


    fun getPopularListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getPopularMovieListNextPage()
            movieList.postValue (response.body()?.results)
        }
    }

    fun getTopRatedListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getTopRatedMovieListNextPage()
            movieList.postValue(response.body()?.results)
        }
    }
}