package com.aldredo.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.adapter.BaseListAdapter
import com.aldredo.home.R
import com.aldredo.home.data.dto.News
import com.aldredo.home.presentation.holder.NewsHolder
import com.aldredo.home.presentation.mvvm.Navigation

class NewsAdapter(private val navigation: Navigation) : BaseListAdapter<News>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsHolder(inflateView, currentList, navigation)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {

            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem
        }
    }
}