package com.example.easymusic.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.easymusic.R
import com.example.easymusic.core.extensions.addRepeatingJob
import com.example.easymusic.databinding.FragmentHomeBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel: HomeViewModel by viewModels()

    private val chartsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HomeNewsAdapter(requireActivity())
    }
    private val chartsLoaderStateAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ChartsLoaderStateAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            charts.adapter = chartsAdapter.withLoadStateHeaderAndFooter(
                header = chartsLoaderStateAdapter,
                footer = chartsLoaderStateAdapter
            )
        }

        chartsAdapter.addLoadStateListener { state ->
            with(binding) {
                charts.isVisible = state.refresh != LoadState.Loading
                progress.isVisible = state.refresh == LoadState.Loading
            }
        }

        viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
            homeViewModel.news
                .collectLatest(chartsAdapter::submitData)
        }
    }
}