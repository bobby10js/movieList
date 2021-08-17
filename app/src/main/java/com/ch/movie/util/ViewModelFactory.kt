package com.ch.movie.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ch.movie.ui.movieList.MovieListViewModel
import com.ch.movie.ui.tvShowList.TvShowListViewModel


class ViewModelFactory(private val viewModel:  ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}