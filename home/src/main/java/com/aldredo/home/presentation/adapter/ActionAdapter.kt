package com.aldredo.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.adapter.BaseRecyclerAdapter
import com.aldredo.home.R
import com.aldredo.home.data.dto.Actions
import com.aldredo.core.base.holder.EmptyHolder
import com.aldredo.home.presentation.mvvm.Navigation

class ActionAdapter(navigation: Navigation) : BaseRecyclerAdapter<Actions>() {
    private val actionAdapter = ItemActionAdapter(navigation)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_action, parent, false)

        inflateView.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager =
                LinearLayoutManager(rootView?.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = actionAdapter
        }
        return EmptyHolder(inflateView)
    }

     override fun submitList(listData: List<Actions>) {
        actionAdapter.submitList(listData)
    }
}
