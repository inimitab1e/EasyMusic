package com.example.easymusic.core_network.models.charts

import com.google.gson.annotations.SerializedName

data class HubDto(
    @SerializedName("actions") val actions: List<ActionDto>? = null
)