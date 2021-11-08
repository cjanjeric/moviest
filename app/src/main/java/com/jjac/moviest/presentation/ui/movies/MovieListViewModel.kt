package com.jjac.moviest.presentation.ui.movies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjac.moviest.repository.MovieRepository
import com.jjac.store.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository): ViewModel(){

    val movies: MutableState<List<Movie>> = mutableStateOf(ArrayList())
    val loading = mutableStateOf(true)
    val visitedMovies: MutableState<List<Movie>> = mutableStateOf(ArrayList())

    init {
        search()
    }

    fun search(){
        viewModelScope.launch {
            /*Hardcoded parameters*/
            val result = repository.search(
                "star",
                "au",
                "movie"
            )

            movies.value = result
            loading.value = false
        }
    }

    val sourceVisitedMovies = repository.getVisitedMovies()

    fun isExist(movie: Movie) = repository.isExist(movie)

    fun updateMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTimeStamp(movie)
        }
    }

    fun insertMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertMovie(movie)
        }
    }
}