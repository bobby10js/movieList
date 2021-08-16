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
import com.ch.movie.util.ExtensionFunctions.append

class MovieListViewModel(private val repository: Repository) : ViewModel() {
    private var pageNumber = 1
    private var movieList : MutableLiveData<Array<Movie>> = MutableLiveData()

    init {
        movieList.value = arrayOf()
    }

    fun getMovieList() : LiveData<Array<Movie>> {
        return movieList
    }

    fun getPopularList(){
        pageNumber = 1
        viewModelScope.launch {
            val response: Response<Movies> = repository.getPopularMovieList(pageNumber)
            movieList.append(response.body()?.results?: arrayOf())
        }
    }

    fun getPopularListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getPopularMovieList(++pageNumber)
            movieList.append (response.body()?.results?: arrayOf())
        }
    }

    fun getTopRatedList(){
        pageNumber = 1
        viewModelScope.launch {
            val response: Response<Movies> = repository.getTopRatedMovieList(pageNumber)
            movieList.append (response.body()?.results?: arrayOf())
        }
    }


    fun getTopRatedListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getTopRatedMovieList(++pageNumber)
            movieList.append(response.body()?.results?: arrayOf())
        }
    }
}

