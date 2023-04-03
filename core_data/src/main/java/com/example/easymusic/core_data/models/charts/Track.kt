package com.example.easymusic.core_data.models.charts

data class Track(
    val artists: List<Artist>?,
    val hub: Hub?,
    val images: Images?,
    val key: String?,
    val share: Share?,
    val subtitle: String?,
    val title: String?
)