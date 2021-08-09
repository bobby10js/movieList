package com.ch.movie.ui.tvShowList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.api.Repository
import com.ch.movie.model.TvShow
import com.ch.movie.model.TvShows

import kotlinx.coroutines.launch
import retrofit2.Response

class TvShowListViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    val tvShowList : MutableLiveData<Array<TvShow>> = MutableLiveData()


    fun getPopularList(){
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getPopularTVList()
            tvShowList.value = response.body()?.results
        }
    }

    fun getTopRatedList(){
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getTopRatedTVList()
            tvShowList.value = response.body()?.results
        }
    }


    fun getPopularListNextPage(){
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getPopularTVListNextPage()
            tvShowList.value = response.body()?.results
        }
    }

    fun getTopRatedListNextPage(){
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getPopularTVListListNextPage()
            tvShowList.value = response.body()?.results
        }
    }
}