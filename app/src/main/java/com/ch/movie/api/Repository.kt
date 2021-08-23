package com.ch.movie.api

import com.ch.movie.model.*
import com.ch.movie.util.Constants.Companion.api_key
import retrofit2.Response
import javax.inject.Inject

class Repository  @Inject constructor(private val API:TmdbAPI){

    suspend fun getMovieDetail(id: Int): Response<Movie>  =  API.getMovieDetail(id, api_key)
    suspend fun getPopularMovieList(pageNumber: Int): Response<Movies>  =  API.getPopularMovieList(api_key, pageNumber)
    suspend fun getTopRatedMovieList(pageNumber: Int): Response<Movies>  =  API.getTopRatedMovieList(api_key, pageNumber)
    suspend fun getSimilarMovieDetail(id: Int): Response<Movies>  =  API.getSimilarMovieDetail(id, api_key)
    suspend fun getMovieCastDetails(id: Int): Response<Casts>  =  API.getMovieCastDetails(id, api_key)

    suspend fun getTvShowDetail(id: Int): Response<TvShow>  =  API.getTvShowDetail(id, api_key)
    suspend fun getPopularTVList(pageNumber: Int): Response<TvShows>  =  API.getPopularTVList(api_key, pageNumber)
    suspend fun getTopRatedTVList(pageNumber: Int): Response<TvShows>  =  API.getTopRatedTVList(api_key, pageNumber)
    suspend fun getSimilarTvShowDetail(id: Int): Response<TvShows>  =  API.getSimilarTvShowDetail(id, api_key)
    suspend fun getTvShowCastDetails(id: Int): Response<Casts> = API.getTvShowCastDetails(id, api_key)

}
