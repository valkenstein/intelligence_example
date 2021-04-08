package com.aldredo.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.adapter.BaseListAdapter
import com.aldredo.home.R
import com.aldredo.home.data.dto.Actions
import com.aldredo.home.presentation.holder.ActionHolder
import com.aldredo.home.presentation.mvvm.Navigation

class ItemActionAdapter(private val navigation: Navigation) :
    BaseListAdapter<Actions>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_circle_insta, parent, false)
        return ActionHolder(inflateView, currentList, navigation)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Actions>() {

            override fun areItemsTheSame(oldItem: Actions, newItem: Actions): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Actions, newItem: Actions): Boolean =
                oldItem == newItem
        }
    }
}