package com.aldredo.notification.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.adapter.BaseRecyclerAdapter
import com.aldredo.notification.R
import com.aldredo.notification.data.model.NotificationModel
import com.aldredo.notification.presentation.holder.NotificationHolder

class NotificationAdapter : BaseRecyclerAdapter<NotificationModel.Result>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NotificationHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false),
            currentList
        )
    }
}