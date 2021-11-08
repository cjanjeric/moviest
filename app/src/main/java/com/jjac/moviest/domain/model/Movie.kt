package com.jjac.store.domain.model

import com.jjac.moviest.database.entity.MovieEntity
import java.io.Serializable
import java.util.*

data class Movie(
    val id: Long? = null,
    val collectionId: Int,
    val trackId: Int,
    var trackName: String? = "",
    val collectionName: String? = "",
    val primaryGenreName: String? = "",
    val collectionPrice: String? = "",
    val shortDescription: String? = "",
    val longDescription: String? = "",
    val previewUrl: String? = "",
    val artworkUrl100: String? = "",
    var timeStamp: Date? = null
) : Serializable

fun Movie.asDatabaseModel() : MovieEntity{
    return MovieEntity(
        id = id,
        collectionId = collectionId,
        trackId = trackId,
        trackName = trackName,
        collectionName = collectionName,
        primaryGenreName = primaryGenreName,
        collectionPrice = collectionPrice,
        shortDescription = shortDescription,
        longDescription = longDescription,
        previewUrl = previewUrl,
        artworkUrl100 = artworkUrl100,
        timeStamp = timeStamp
    )
}
