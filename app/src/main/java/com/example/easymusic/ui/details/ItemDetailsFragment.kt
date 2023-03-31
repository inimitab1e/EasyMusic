package com.example.easymusic.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.easymusic.R
import com.example.easymusic.databinding.FragmentItemDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsFragment : Fragment(R.layout.fragment_item_details) {

    private val binding by viewBinding(FragmentItemDetailsBinding::bind)
    private val itemDetailsViewModel: ItemDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}