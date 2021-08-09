package com.ch.movie.ui.tvShowList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ch.movie.api.Repository


class TvShowListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowListViewModel(repository) as T
    }

}