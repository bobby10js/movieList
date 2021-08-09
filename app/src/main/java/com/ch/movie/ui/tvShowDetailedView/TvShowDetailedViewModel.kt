package com.ch.movie.ui.movieDetailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.api.Repository
import com.ch.movie.model.*
import kotlinx.coroutines.launch
import retrofit2.Response

class TvShowDetailedViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    val tvShowDetails: MutableLiveData<TvShow> = MutableLiveData()
    val similarTvShowDetails: MutableLiveData<TvShows> = MutableLiveData()
    val castDetails: MutableLiveData<Casts> = MutableLiveData()

    fun getDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<TvShow> = repository.getTvShowDetail(id)
            tvShowDetails.value = response.body()
        }
    }

    fun getSimilarTvShowDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getSimilarTvShowDetail(id)
            similarTvShowDetails.value = response.body()
        }
    }

    fun getCastDetails(id: Int) {
        viewModelScope.launch {
            val response: Response<Casts> = repository.getTvShowCastDetails(id)
            castDetails.value = response.body()
        }
    }

}
