package com.aldredo.notification.presentation.holder

import android.view.View
import com.aldredo.core.base.holder.BaseHolder
import com.aldredo.notification.data.model.NotificationModel

class NotificationHolder(
    private val view: View,
    private val currentList: List<NotificationModel.Result>
) : BaseHolder(view) {
    override fun bind() {
    }
}