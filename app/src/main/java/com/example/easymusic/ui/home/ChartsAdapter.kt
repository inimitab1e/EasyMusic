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
import com.example.easymusic.core_data.models.charts.Charts
import com.example.easymusic.databinding.ItemNewsBinding

class HomeNewsAdapter(context: Context) :
    PagingDataAdapter<Charts, HomeNewsViewHolder>(ArticleDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        return HomeNewsViewHolder(layoutInflater.inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(ItemNewsBinding::bind)

    fun bind(charts: Charts?) {
        with(viewBinding) {
            image.load(charts?.tracks?.get(0)?.images?.coverart) {
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            title.text = charts?.tracks?.get(0)?.title
        }
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Charts>() {

    override fun areItemsTheSame(oldItem: Charts, newItem: Charts): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Charts, newItem: Charts): Boolean {
        return oldItem.tracks?.get(0)?.title == newItem.tracks?.get(0)?.title
                && oldItem.tracks?.get(0)?.images?.coverart == newItem.tracks?.get(0)?.images?.coverart
    }
}