package com.jjac.moviest.network.response

import com.google.gson.annotations.SerializedName
import com.jjac.moviest.network.model.MovieDto

data class MovieSearchResponse(
    @SerializedName("resultCount") var count: Int,
    @SerializedName("results") var movies: List<MovieDto>,
)
