package com.example.easymusic.ui.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.easymusic.R
import com.example.easymusic.core_data.models.charts.Track
import com.example.easymusic.databinding.ItemChartsBinding


class HomeNewsAdapter(context: Context) :
    PagingDataAdapter<Track, HomeNewsViewHolder>(ArticleDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        return HomeNewsViewHolder(layoutInflater.inflate(R.layout.item_charts, parent, false))
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding by viewBinding(ItemChartsBinding::bind)

    fun bind(charts: Track?) {
        with(binding) {
            image.load(charts?.images?.coverart) {
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            title.text = charts?.title
        }
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Track>() {

    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.title == newItem.title
                && oldItem.images?.coverart == newItem.images?.coverart
    }
}