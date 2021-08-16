package com.ch.movie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "movie_list")
data class Movie(
    @ColumnInfo var adult : Boolean,
    @ColumnInfo var backdrop_path: String,
    @ColumnInfo var original_language: String,
    @ColumnInfo var original_title: String,
    @ColumnInfo var overview: String,
    @ColumnInfo var popularity: Double,
    @ColumnInfo var poster_path: String,
    @ColumnInfo var release_date :Date,
    @ColumnInfo var title : String,
    @ColumnInfo var video: Boolean,
    @ColumnInfo var vote_average : Float,
    @ColumnInfo var vote_count : Int,
//    @Ignore var genre_ids: IntArray,
    @PrimaryKey var id: Int,
    @ColumnInfo var genres: Array<Genre>,
    @ColumnInfo var runtime: Int?
)