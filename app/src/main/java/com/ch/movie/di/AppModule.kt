package com.ch.movie.di


import android.content.Context
import androidx.fragment.app.viewModels
import com.ch.movie.api.Repository
import com.ch.movie.api.TmdbAPI
import com.ch.movie.db.WatchListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(API:TmdbAPI): Repository{
        return Repository(API)
    }

    @Provides
    @Singleton
    fun provideWatchLaterRepository(watchListDao: WatchListDao): com.ch.movie.db.Repository {
        return com.ch.movie.db.Repository(watchListDao)
    }
//    @Provides
//    fun provideMovieDetailedViewModel(): Class<MovieDetailedViewModel> {
//        return MovieDetailedViewModel::class.java
//    }
//
//    @Provides
//    fun provideWatchLaterViewModel(): Class<WatchLaterViewModel> {
//        return WatchLaterViewModel::class.java
//    }
//
//    @Provides
//    fun provideTvShowListViewModel(): Class<TvShowListViewModel> {
//        return TvShowListViewModel::class.java
//    }
//
//    @Provides
//    fun provideTvShowDetailedViewModel(): Class<TvShowDetailedViewModel> {
//        return TvShowDetailedViewModel::class.java
//    }
//
//    @Provides
//    fun provideMovieListViewModel(): Class<MovieListViewModel> {
//        return MovieListViewModel::class.java
//    }

//    @Provides
//    @ActivityScoped
//    fun provideMovieListViewModel(@ActivityContext context: Context): MovieListViewModel {
//        return ViewModelProvider(context as ViewModelStoreOwner).get(MovieListViewModel::class.java)
//    }
//
//    @Provides
//    @ActivityScoped
//    fun provideMovieDetailedViewModel(@ActivityContext context: Context): MovieDetailedViewModel {
//        return ViewModelProvider(context as ViewModelStoreOwner).get(MovieDetailedViewModel::class.java)
//    }
//
//    @Provides
//    @ActivityScoped
//    fun provideTvShowListViewModel(@ActivityContext context: Context): TvShowListViewModel {
//        return ViewModelProvider(context as ViewModelStoreOwner).get(TvShowListViewModel::class.java)
//    }
//    @Provides
//    @ActivityScoped
//    fun provideTvShowDetailedViewModel(@ActivityContext context: Context): TvShowDetailedViewModel {
//        return ViewModelProvider(context as ViewModelStoreOwner).get(TvShowDetailedViewModel::class.java)
//    }

//    @Provides
//    @ActivityContext
//    fun provideWatchLaterViewModel(@ActivityContext context: Context): WatchLaterViewModel {
//        return ViewModelProvider(context as ViewModelStoreOwner).get(WatchLaterViewModel::class.java)
//    }

}