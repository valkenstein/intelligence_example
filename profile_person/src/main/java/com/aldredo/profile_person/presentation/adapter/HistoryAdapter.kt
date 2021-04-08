package com.aldredo.profile_person.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.adapter.BaseRecyclerAdapter
import com.aldredo.profile_person.R
import com.aldredo.profile_person.data.model.HistoryOrderModel
import com.aldredo.profile_person.presentation.holder.HistoryHolder

class HistoryAdapter() : BaseRecyclerAdapter<HistoryOrderModel.ItemHistory>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false),
            currentList
        )
    }
}