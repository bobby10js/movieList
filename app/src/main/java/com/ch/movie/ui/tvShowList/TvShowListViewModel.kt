package com.ch.movie.ui.tvShowList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.api.Repository
import com.ch.movie.model.TvShow
import com.ch.movie.model.TvShows
import kotlinx.coroutines.launch
import retrofit2.Response
import com.ch.movie.util.ExtensionFunctions.append

class TvShowListViewModel(private val repository: Repository) : ViewModel() {
    private var pageNumber = 0
    private val tvShowList : MutableLiveData<Array<TvShow>> = MutableLiveData()

    init {
        tvShowList.value = arrayOf()
    }

    fun getTvShowList() : LiveData<Array<TvShow>>{
        return tvShowList
    }


    fun getPopularListNextPage(){
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getPopularTVList(++pageNumber)
            tvShowList.append (response.body()?.results?: arrayOf())
        }
    }



    fun getTopRatedListNextPage(){
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getPopularTVList(++pageNumber)
            tvShowList.append (response.body()?.results?: arrayOf())
        }
    }
}

