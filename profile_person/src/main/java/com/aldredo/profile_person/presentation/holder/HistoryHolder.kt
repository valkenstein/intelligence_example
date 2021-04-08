package com.aldredo.profile_person.presentation.holder

import android.view.View
import android.widget.TextView
import com.aldredo.core.base.holder.BaseHolder
import com.aldredo.profile_person.R
import com.aldredo.profile_person.data.model.HistoryOrderModel

class HistoryHolder(
    private val view: View,
    private val currentList: List<HistoryOrderModel.ItemHistory>
) : BaseHolder(view) {

    override fun bind() {
        view.findViewById<TextView>(R.id.id_order).text =
            currentList[bindingAdapterPosition].client_1c_id
    }
}