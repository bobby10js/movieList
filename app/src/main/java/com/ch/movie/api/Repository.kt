package com.ch.movie.api

import com.ch.movie.model.*
import com.ch.movie.util.Constants.Companion.api_key
import retrofit2.Response
class Repository {

    suspend fun getMovieDetail(id: Int): Response<Movie> {
        return RetrofitInstance.API.getMovieDetail(id, api_key)
    }

    suspend fun getPopularMovieList(pageNumber: Int): Response<Movies> {
        return RetrofitInstance.API.getPopularMovieList(api_key, pageNumber)
    }

    suspend fun getTopRatedMovieList(pageNumber: Int): Response<Movies> {
        return RetrofitInstance.API.getTopRatedMovieList(api_key, pageNumber)
    }

    suspend fun getSimilarMovieDetail(id: Int): Response<Movies> {
        return RetrofitInstance.API.getSimilarMovieDetail(id, api_key)
    }

    suspend fun getMovieCastDetails(id: Int): Response<Casts> {
        return RetrofitInstance.API.getMovieCastDetails(id, api_key)
    }


    suspend fun getTvShowDetail(id: Int): Response<TvShow> {
        return RetrofitInstance.API.getTvShowDetail(id, api_key)
    }

    suspend fun getPopularTVList(pageNumber: Int): Response<TvShows> {
        return RetrofitInstance.API.getPopularTVList(api_key, pageNumber)
    }


    suspend fun getTopRatedTVList(pageNumber: Int): Response<TvShows> {
        return RetrofitInstance.API.getTopRatedTVList(api_key, pageNumber)
    }

    suspend fun getSimilarTvShowDetail(id: Int): Response<TvShows> {
        return RetrofitInstance.API.getSimilarTvShowDetail(id, api_key)
    }

    suspend fun getTvShowCastDetails(id: Int): Response<Casts> {
        return RetrofitInstance.API.getTvShowCastDetails(id, api_key)
    }

}
