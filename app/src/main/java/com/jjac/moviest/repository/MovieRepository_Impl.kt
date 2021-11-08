package com.jjac.moviest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jjac.moviest.database.MoviestDb
import com.jjac.moviest.database.entity.asDomainModel
import com.jjac.moviest.network.MovieService
import com.jjac.moviest.network.model.MovieDtoMapper
import com.jjac.store.domain.model.Movie
import com.jjac.store.domain.model.asDatabaseModel

class MovieRepository_Impl(
    private val movieService: MovieService,
    private val mapper: MovieDtoMapper,
    private val localDataSource: MoviestDb
) : MovieRepository {

    override suspend fun search(term: String, country: String, media: String): List<Movie> {
        return mapper.toDomainList(movieService.search(term, country, media).movies)
    }

    override fun getVisitedMovies(): LiveData<List<Movie>> {
       return Transformations.map(localDataSource.movieDao.getPreviousVisitedMovies()){
            it.asDomainModel()
        }
    }

    override suspend fun updateTimeStamp(movie: Movie) {
        localDataSource.movieDao.update(movie.asDatabaseModel())
    }

    override suspend fun insertMovie(movie: Movie) {
        localDataSource.movieDao.insert(movie.asDatabaseModel())
    }

    override fun isExist(movie: Movie): LiveData<Boolean> {
        return localDataSource.movieDao.isExists(movie.trackId)
    }
}