package com.jjac.moviest.network.model

import com.jjac.store.domain.model.Movie
import com.jjac.store.domain.util.DomainMapper
import javax.inject.Inject

class MovieDtoMapper @Inject constructor(): DomainMapper<MovieDto, Movie> {
    override fun mapFromDomainModel(domainModel: Movie): MovieDto {
        TODO("Not yet implemented")
    }

    override fun mapToDomainModel(model: MovieDto): Movie {
        return Movie(
            id = model.id,
            trackId = model.trackId,
            collectionId = model.collectionId,
            trackName = model.trackName,
            collectionName = model.collectionName,
            primaryGenreName = model.primaryGenreName,
            collectionPrice = model.collectionPrice,
            shortDescription = model.shortDescription,
            longDescription = model.longDescription,
            artworkUrl100 = model.artworkUrl100,
            previewUrl = model.previewUrl
        )
    }

    fun toDomainList(initial: List<MovieDto>): List<Movie>{
        return initial.map { mapToDomainModel(it) }
    }
}