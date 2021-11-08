package com.jjac.moviest.di

import com.jjac.moviest.database.MoviestDb
import com.jjac.moviest.network.MovieService
import com.jjac.moviest.network.model.MovieDtoMapper
import com.jjac.moviest.repository.MovieRepository
import com.jjac.moviest.repository.MovieRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService,
        movieMapper: MovieDtoMapper,
        movieDb: MoviestDb
    ): MovieRepository{
        return MovieRepository_Impl(
            movieService = movieService,
            mapper = movieMapper,
            localDataSource = movieDb
        )
    }
}