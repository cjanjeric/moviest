package com.jjac.moviest.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieDto (
    @SerializedName("id") var id: Long? = null,
    @SerializedName("collectionId") var collectionId: Int,
    @SerializedName("trackId") var trackId: Int,
    @SerializedName("trackName") var trackName: String? = "",
    @SerializedName("collectionName") var collectionName: String? = "",
    @SerializedName("primaryGenreName") var primaryGenreName: String? = "",
    @SerializedName("collectionPrice") var collectionPrice: String? = "",
    @SerializedName("shortDescription") var shortDescription: String? = "",
    @SerializedName("longDescription") var longDescription: String? = "",
    @SerializedName("previewUrl") var previewUrl: String? = "",
    @SerializedName("artworkUrl100") var artworkUrl100: String
)