package com.example.easymusic.core_data.repository.charts

import androidx.paging.PagingSource
import com.example.easymusic.core_data.models.charts.Track
import javax.inject.Inject

class ChartsRepositoryImpl @Inject constructor(
    private val chartsPagingSource: ChartsPagingSource.Factory
) : ChartsRepository {

    override fun getPagedCharts(): PagingSource<Int, Track> {
        return chartsPagingSource.create()
    }
}