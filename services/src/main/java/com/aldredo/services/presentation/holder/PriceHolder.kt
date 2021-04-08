package com.aldredo.services.presentation.holder

import android.view.View
import android.widget.TextView
import com.aldredo.core.base.holder.BaseHolder
import com.aldredo.services.R
import com.aldredo.services.domain.dto.PriceDto

class PriceHolder(view: View, private val currentList: MutableList<PriceDto>) : BaseHolder(view) {
    private val key = view.findViewById<TextView>(R.id.key)
    private val value = view.findViewById<TextView>(R.id.value)

    override fun bind() {
        key.text = currentList[bindingAdapterPosition].key
        value.text = currentList[bindingAdapterPosition].value
    }
}