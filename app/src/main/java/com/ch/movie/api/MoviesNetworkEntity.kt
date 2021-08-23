package com.ch.movie.api

import androidx.annotation.experimental.Experimental
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.ch.movie.model.Genre
import com.ch.movie.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class MoviesNetworkEntity(
    @SerializedName("page")
    @Expose
    var page: Int,
    @SerializedName("result")
    @Expose
    var results : Array<Movie>
)