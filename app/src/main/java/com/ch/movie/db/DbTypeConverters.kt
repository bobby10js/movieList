package com.ch.movie.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.room.TypeConverter
import com.ch.movie.model.Genre
import com.ch.movie.model.Movie
import com.google.gson.Gson
import java.util.*

class DbTypeConverters {

    @TypeConverter
    fun fromDateToLong(date: Date) = date.time

    @TypeConverter
    fun fromLongToDate(timestamp: Long) = Date(timestamp)

    @TypeConverter
    fun fromIntArrayToString(genre_ids: IntArray): String = Gson().toJson(genre_ids)

    @TypeConverter
    fun fromStringToIntArray(genre_ids: String): IntArray = Gson().fromJson(genre_ids, IntArray::class.java)

    @TypeConverter
    fun fromGenreArrayToString(genres: Array<Genre>): String = Gson().toJson(genres)

    @TypeConverter
    fun jsonStringToList(value: String): Array<Genre> = Gson().fromJson(value, Array<Genre>::class.java)



    @TypeConverter
    fun fromStringArrayToString(originCountries: Array<String>): String = Gson().toJson(originCountries)

    @TypeConverter
    fun jsonStringToStringArray(value: String): Array<String> = Gson().fromJson(value, Array<String>::class.java)
}
