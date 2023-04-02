package com.example.easymusic.core_network.models.charts

import com.google.gson.annotations.SerializedName

data class ChartsDto(
    @SerializedName("tracks") val tracks: List<TrackDto>? = null
)