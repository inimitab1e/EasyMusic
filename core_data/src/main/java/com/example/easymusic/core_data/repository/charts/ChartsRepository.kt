package com.example.easymusic.core_data.repository.charts

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.easymusic.core_data.models.charts.Charts
import kotlinx.coroutines.flow.Flow

interface ChartsRepository {

    fun getPagedCharts(): PagingSource<Int, Charts>
}