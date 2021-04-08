package com.aldredo.services.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.aldredo.core.base.adapter.BaseListAdapter
import com.aldredo.services.R
import com.aldredo.services.di.ServicesScope
import com.aldredo.services.domain.dto.PriceDto
import com.aldredo.services.presentation.holder.PriceHolder
import javax.inject.Inject

@ServicesScope
class PriceAdapter @Inject constructor() : BaseListAdapter<PriceDto>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PriceHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_price, parent, false),
        currentList
    )

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PriceDto>() {

            override fun areItemsTheSame(oldItem: PriceDto, newItem: PriceDto): Boolean =
                oldItem.key == newItem.key

            override fun areContentsTheSame(oldItem: PriceDto, newItem: PriceDto): Boolean =
                oldItem == newItem
        }
    }
}