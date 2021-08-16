package com.ch.movie.model

import java.util.*

data class TvShow (
    var poster_path: String,
    var popularity: Double,
    var id: Int,
    var backdrop_path: String,
    var vote_average : Float,
    var overview: String,
    var first_air_date: Date?,
    var origin_country : Array<String>,
    var genre_ids: IntArray,
    var original_language: String,
    var vote_count : Int,
    var name : String,
    var original_name: String,
    var genres: Array<Genre>?,
    var episode_run_time: IntArray?,
    )