package com.ch.movie.api

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.ch.movie.db.WatchListDao
import com.ch.movie.db.WatchListDatabase
import com.ch.movie.model.*
import com.ch.movie.util.Constants.Companion.api_key
import retrofit2.Response
class Repository() {

    private var pageNumber: Int = 1

    suspend fun getPopularMovieList(): Response<Movies> {
        pageNumber = 1
        return RetrofitInstance.API.getPopularMovieList(api_key, pageNumber)
    }

    suspend fun getPopularMovieListNextPage(): Response<Movies> {
        return RetrofitInstance.API.getPopularMovieList(api_key, ++pageNumber)
    }

    suspend fun getTopRatedMovieList(): Response<Movies> {
        pageNumber = 1
        return RetrofitInstance.API.getTopRatedMovieList(api_key, pageNumber)
    }

    suspend fun getTopRatedMovieListNextPage(): Response<Movies> {
        return RetrofitInstance.API.getTopRatedMovieList(api_key, ++pageNumber)
    }

    suspend fun getMovieDetail(id: Int): Response<Movie> {
        return RetrofitInstance.API.getMovieDetail(id, api_key)
    }

    suspend fun getSimilarMovieDetail(id: Int): Response<Movies> {
        return RetrofitInstance.API.getSimilarMovieDetail(id, api_key)
    }

    suspend fun getMovieCastDetails(id: Int): Response<Casts> {
        return RetrofitInstance.API.getMovieCastDetails(id, api_key)
    }
    suspend fun getTvShowCastDetails(id: Int): Response<Casts> {
        return RetrofitInstance.API.getTvShowCastDetails(id, api_key)
    }
    suspend fun getTvShowDetail(id: Int): Response<TvShow> {
        return RetrofitInstance.API.getTvShowDetail(id, api_key)
    }


    suspend fun getSimilarTvShowDetail(id: Int): Response<TvShows> {
        return RetrofitInstance.API.getSimilarTvShowDetail(id, api_key)
    }


    suspend fun getPopularTVList(): Response<TvShows> {
        pageNumber = 1
        return RetrofitInstance.API.getPopularTVList(api_key, pageNumber)
    }

    suspend fun getPopularTVListNextPage(): Response<TvShows> {
        return RetrofitInstance.API.getPopularTVList(api_key, ++pageNumber)
    }


    suspend fun getTopRatedTVList(): Response<TvShows> {
        pageNumber = 1
        return RetrofitInstance.API.getTopRatedTVList(api_key, pageNumber)
    }

    suspend fun getPopularTVListListNextPage(): Response<TvShows> {
        return RetrofitInstance.API.getTopRatedTVList(api_key, ++pageNumber)
    }







}
