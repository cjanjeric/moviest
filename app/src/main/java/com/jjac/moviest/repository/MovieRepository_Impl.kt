package com.jjac.moviest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jjac.moviest.database.MoviestDb
import com.jjac.moviest.database.entity.asDomainModel
import com.jjac.moviest.network.MovieService
import com.jjac.moviest.network.model.MovieDtoMapper
import com.jjac.moviest.network.response.MovieSearchResponse
import com.jjac.store.domain.model.Movie
import com.jjac.store.domain.model.asDatabaseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository_Impl(
    private val movieService: MovieService,
    private val mapper: MovieDtoMapper,
    private val localDataSource: MoviestDb
) : MovieRepository {

    override suspend fun updateMovies(term: String, country: String, media: String) {
        movieService.search(term, country, media).enqueue(object : Callback<MovieSearchResponse> {
            override fun onResponse(
                call: Call<MovieSearchResponse>,
                response: Response<MovieSearchResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { movies ->
                        Thread {
                            localDataSource.movieDao.updateMovies(mapper.toDomainList(movies.movies))
                        }.start()
                    }
                }
            }

            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {}
        })
    }

    override fun getVisitedMovies(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.movieDao.getPreviousVisitedMovies()) {
            it.asDomainModel()
        }
    }

    override fun getMovies(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.movieDao.getMovies()) {
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
