package com.example.easymusic.core_data.repository.charts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.easymusic.core_data.mappers.charts.toCharts
import com.example.easymusic.core_data.models.charts.Track
import com.example.easymusic.core_network.source.ApiService
import com.example.easymusic.core_network.utils.result.Result
import com.example.easymusic.core_network.utils.result.asFailure
import com.example.easymusic.core_network.utils.result.asSuccess
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ChartsPagingSource @AssistedInject constructor(
    private val apiService: ApiService
) : PagingSource<Int, Track>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize.coerceAtMost(ApiService.MAX_PAGE_SIZE).toString()
        return when(val response = apiService.getCharts(pageNumber, pageSize)) {
            is Result.Success -> {
                val charts = response.asSuccess().value.toCharts().tracks ?: listOf()
                val nextPageNumber = if (charts.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(charts, prevPageNumber, nextPageNumber)
            }
            is Result.Failure<*> -> LoadResult.Error(response.asFailure().error!!)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }

    @AssistedFactory
    interface Factory {
        fun create(): ChartsPagingSource
    }
}