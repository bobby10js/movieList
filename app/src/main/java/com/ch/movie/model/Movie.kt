package com.ch.movie.model

import java.util.*
data class Movie(
    var adult : Boolean,
    var backdrop_path: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date :Date,
    var title : String,
    var video: Boolean,
    var vote_average : Float,
    var vote_count : Int,
    var genre_ids: IntArray,
    var id: Int,
    var genres: Array<Genres>?,
    var runtime: Int?
)