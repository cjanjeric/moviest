package com.jjac.moviest.network.model

import com.jjac.moviest.database.entity.MovieEntity
import com.jjac.store.domain.model.Movie
import com.jjac.store.domain.util.DomainMapper
import javax.inject.Inject

class MovieDtoMapper @Inject constructor(): DomainMapper<MovieDto, MovieEntity> {

    override fun mapFromDomainModel(domainModel: MovieEntity): MovieDto {
        TODO("Not yet implemented")
    }

    override fun mapToDomainModel(model: MovieDto): MovieEntity {
        return MovieEntity(
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

    fun toDomainList(initial: List<MovieDto>): List<MovieEntity>{
        return initial.map { mapToDomainModel(it) }
    }
}