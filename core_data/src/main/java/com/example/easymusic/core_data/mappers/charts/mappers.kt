package com.example.easymusic.core_data.mappers.charts

import com.example.easymusic.core_data.models.charts.*
import com.example.easymusic.core_network.models.charts.*

fun ChartsDto.toCharts(): Charts = Charts(
    tracks = tracks?.map { it.toTrack() }
)

fun TrackDto.toTrack(): Track = Track(
    artists = artists?.map { it.toArtist() },
    hub = hub?.toHub(),
    images = images?.toImages(),
    key = key,
    share = share?.toShare(),
    subtitle = subtitle,
    title = title
)

fun ArtistDto.toArtist(): Artist = Artist(
    adamid = adamid,
    alias = alias,
    id = id
)

fun HubDto.toHub(): Hub = Hub(
    actions = actions?.map { it.toAction() }
)

fun ActionDto.toAction(): Action = Action(
    uri = uri
)

fun ImagesDto.toImages(): Images = Images(
    coverart = coverart
)

fun ShareDto.toShare(): Share = Share(
    href = href
)