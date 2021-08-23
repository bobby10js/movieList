package com.ch.movie.di

import android.content.Context
import com.ch.movie.api.Repository
import com.ch.movie.api.TmdbAPI
import com.ch.movie.db.WatchListDao
import com.ch.movie.db.WatchListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideWatchListDao(@ApplicationContext context: Context): WatchListDao {
        return WatchListDatabase.getInstance(context).watchListDao()
    }
}