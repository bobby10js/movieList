package com.ch.movie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ch.movie.model.Movie

@Database(entities = [Movie::class],version = 1)
@TypeConverters(DbTypeConverters::class)
abstract class WatchListDatabase: RoomDatabase() {
     abstract fun watchListDao() : WatchListDao

     companion object {
         var instance:WatchListDatabase? = null
         @Synchronized
         fun getInstance(context: Context): WatchListDatabase {
             if (instance == null) {
                 instance = Room.databaseBuilder(
                     context,
                     WatchListDatabase::class.java, "watch_list_db"
                 ).build()
             }
             return instance as WatchListDatabase
         }

     }
}