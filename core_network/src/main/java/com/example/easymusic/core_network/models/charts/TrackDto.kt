package com.example.easymusic.core_network.models.charts

import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("artists") val artists: List<ArtistDto>? = null,
    @SerializedName("hub") val hub: HubDto? = null,
    @SerializedName("images") val images: ImagesDto? = null,
    @SerializedName("key") val key: String? = null,
    @SerializedName("share") val share: ShareDto? = null,
    @SerializedName("subtitle") val subtitle: String? = null,
    @SerializedName("title") val title: String? = null
)