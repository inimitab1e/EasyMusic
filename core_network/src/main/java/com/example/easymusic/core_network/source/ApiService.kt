package com.example.easymusic.core_network.source

import com.example.easymusic.core_network.models.charts.ChartsDto
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.easymusic.core_network.utils.result.Result

interface ApiService {

    @GET("charts/track")
    suspend fun getCharts(
        @Query("pageSize") pageSize: String? = null,
        @Query("startFrom") @androidx.annotation.IntRange(from = 1) page: Int = 1
    ): Result<ChartsDto>
}