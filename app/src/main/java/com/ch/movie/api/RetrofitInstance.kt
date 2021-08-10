package com.ch.movie.api

import com.ch.movie.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import com.google.gson.Gson




object RetrofitInstance {
    private var gsonDate: Gson = GsonBuilder()
        .setDateFormat("YYYY-MM-DD")
        .create()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonDate))
            .build()
    }

    val API:TmdbAPI by lazy {
        retrofit.create(TmdbAPI::class.java)

    }



}