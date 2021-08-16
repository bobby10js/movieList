package com.ch.movie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "tv_show_list")
data class TvShow (
    @ColumnInfo var poster_path: String,
    @ColumnInfo var popularity: Double,
    @PrimaryKey var id: Int,
    @ColumnInfo var backdrop_path: String,
    @ColumnInfo var vote_average : Float,
    @ColumnInfo var overview: String,
    @ColumnInfo var first_air_date: Date?,
    @ColumnInfo var origin_country : Array<String>,
//    var genre_ids: IntArray,
    @ColumnInfo var original_language: String,
    @ColumnInfo var vote_count : Int,
    @ColumnInfo var name : String,
    @ColumnInfo var original_name: String,
    @ColumnInfo var genres: Array<Genre>?,
    @ColumnInfo var episode_run_time: IntArray?,
    )