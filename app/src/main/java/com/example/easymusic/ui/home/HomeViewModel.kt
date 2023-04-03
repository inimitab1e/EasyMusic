package com.example.easymusic.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.easymusic.core_data.models.charts.Charts
import com.example.easymusic.core_data.repository.charts.ChartsPagingSource
import com.example.easymusic.core_data.repository.charts.ChartsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val chartsRepository: ChartsRepository
) : ViewModel() {

    private var newPagingSource: PagingSource<*, *>? = null

    val news: StateFlow<PagingData<Charts>> = newPager().flow
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(): Pager<Int, Charts> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            chartsRepository.getPagedCharts().also { newPagingSource = it }
        }
    }
}