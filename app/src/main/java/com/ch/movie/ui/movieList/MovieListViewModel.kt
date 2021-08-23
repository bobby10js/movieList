package com.ch.movie.ui.movieList

import androidx.lifecycle.*
import com.ch.movie.api.Repository
import com.ch.movie.model.Movie
import com.ch.movie.model.Movies

import kotlinx.coroutines.launch
import retrofit2.Response
import com.ch.movie.util.ExtensionFunctions.append
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel

class MovieListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var pageNumber = 0
    private var movieList : MutableLiveData<Array<Movie>> = MutableLiveData()

    init {
        movieList.value = arrayOf()
    }

    fun getMovieList() : LiveData<Array<Movie>> {
        return movieList
    }



    fun getPopularListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getPopularMovieList(++pageNumber)
            movieList.append (response.body()?.results?: arrayOf())
        }
    }


    fun getTopRatedListNextPage(){
        viewModelScope.launch {
            val response: Response<Movies> = repository.getTopRatedMovieList(++pageNumber)
            movieList.append(response.body()?.results?: arrayOf())
        }
    }

    val user = liveData {
        val data =  repository.getTopRatedMovieList(++pageNumber).body()?.results?: arrayOf() // loadUser is a suspend function.
        emit(data)
    }



}

