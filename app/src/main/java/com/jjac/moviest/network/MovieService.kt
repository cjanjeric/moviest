package com.jjac.moviest.network

import com.jjac.moviest.network.response.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("search")
    suspend fun search(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): MovieSearchResponse
}