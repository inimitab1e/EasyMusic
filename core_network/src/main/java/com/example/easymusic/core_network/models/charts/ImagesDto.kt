package com.example.easymusic.core_network.models.charts

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("coverart") val coverart: String? = null
)