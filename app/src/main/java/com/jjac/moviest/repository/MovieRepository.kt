package com.jjac.moviest.repository

import androidx.lifecycle.LiveData
import com.jjac.store.domain.model.Movie

interface MovieRepository {
    suspend fun updateMovies(term: String, country: String, media: String)
    suspend fun updateTimeStamp(movie: Movie)
    suspend fun insertMovie(movie: Movie)
    fun isExist(movie: Movie): LiveData<Boolean>
    fun getVisitedMovies(): LiveData<List<Movie>>
    fun getMovies(): LiveData<List<Movie>>
}