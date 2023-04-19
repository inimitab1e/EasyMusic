package com.example.easymusic.core_data.repository.charts

import androidx.paging.PagingSource
import com.example.easymusic.core_data.models.charts.Track

interface ChartsRepository {

    fun getPagedCharts(): PagingSource<Int, Track>
}