package com.ch.movie.ui.tvShowDetailedView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.movie.api.Repository
import com.ch.movie.model.*
import kotlinx.coroutines.launch
import retrofit2.Response

class TvShowDetailedViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    private val tvShowDetails: MutableLiveData<TvShow> = MutableLiveData()
    private val similarTvShowDetails: MutableLiveData<TvShows> = MutableLiveData()
    private val castDetails: MutableLiveData<Casts> = MutableLiveData()

    fun getTvShowDetail() : LiveData<TvShow> {
        return tvShowDetails
    }

    fun getSimilarTvShowDetails() : LiveData<TvShows> {
        return similarTvShowDetails
    }
    fun getCastDetails() : LiveData<Casts> {
        return castDetails
    }

    fun setDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<TvShow> = repository.getTvShowDetail(id)
            tvShowDetails.value = response.body()
        }
    }

    fun setSimilarTvShowDetail(id: Int) {
        viewModelScope.launch {
            val response: Response<TvShows> = repository.getSimilarTvShowDetail(id)
            similarTvShowDetails.value = response.body()
        }
    }

    fun setCastDetails(id: Int) {
        viewModelScope.launch {
            val response: Response<Casts> = repository.getTvShowCastDetails(id)
            castDetails.value = response.body()
        }
    }

}
