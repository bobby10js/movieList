package com.ch.movie.di

import com.ch.movie.api.TmdbAPI
import com.ch.movie.util.Constants
import com.ch.movie.util.CustomDate
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitGson(): Gson {
        return  GsonBuilder()
            .setDateFormat("YYYY-MM-DD")
            .registerTypeAdapter(Date::class.java, CustomDate())
            .create()
    }


    @Provides
    @Singleton
    fun provideRetrofitInstance(RetrofitGson:Gson): TmdbAPI {
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(RetrofitGson))
            .build()
            .create(TmdbAPI::class.java)
    }


}
