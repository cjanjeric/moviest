package com.jjac.moviest.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jjac.store.domain.model.Movie
import java.util.*

@Entity
data class MovieEntity constructor(
    var id: Long? = null,
    val collectionId: Int,
    @PrimaryKey
    val trackId: Int,
    val trackName: String? = "",
    val collectionName: String? = "",
    val primaryGenreName: String? = "",
    val collectionPrice: String? = "",
    val shortDescription: String? = "",
    val longDescription: String? = "",
    val previewUrl: String? = "",
    val artworkUrl100: String? = "",
    val timeStamp: Date? = null
)

fun List<MovieEntity>.asDomainModel(): List<Movie>{
    return map {
        Movie(
            id = it.id,
            collectionId = it.collectionId,
            trackId = it.trackId,
            trackName = it.trackName,
            collectionName = it.collectionName,
            primaryGenreName = it.primaryGenreName,
            collectionPrice = it.collectionPrice,
            shortDescription = it.shortDescription,
            longDescription = it.longDescription,
            previewUrl = it.previewUrl,
            artworkUrl100 = it.artworkUrl100,
            timeStamp = it.timeStamp
        )
    }
}
