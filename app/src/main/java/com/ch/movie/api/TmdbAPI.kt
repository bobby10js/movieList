package com.ch.movie.api

import com.ch.movie.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    @GET("movie/popular")
    suspend fun getPopularMovieList(@Query("api_key") api_key: String, @Query("page") page: Int ): Response<Movies>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(@Query("api_key") api_key: String, @Query("page") page: Int): Response<Movies>

    @GET("tv/popular")
    suspend fun getPopularTVList(@Query("api_key") api_key: String, @Query("page") page: Int ): Response<TvShows>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVList(@Query("api_key") api_key: String, @Query("page") page: Int): Response<TvShows>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") Id: Int, @Query("api_key") api_key: String): Response<Movie>

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(@Path("tv_id") Id: Int, @Query("api_key") api_key: String): Response<TvShow>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovieDetail(@Path("movie_id") Id: Int, @Query("api_key") api_key: String): Response<Movies>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCastDetails(@Path("movie_id") Id: Int, @Query("api_key") api_key: String): Response<Casts>

    @GET("tv/{tv_id}/credits")
    suspend fun getTvShowCastDetails(@Path("tv_id") Id: Int, @Query("api_key") api_key: String): Response<Casts>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShowDetail(@Path("tv_id") Id: Int, @Query("api_key") api_key: String): Response<TvShows>

}