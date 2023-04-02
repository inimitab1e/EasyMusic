package com.example.easymusic.core_network.models.charts

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("adamid") val adamid: String? = null,
    @SerializedName("alias") val alias: String? = null,
    @SerializedName("id") val id: String? = null
)