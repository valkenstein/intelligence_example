package com.aldredo.services.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.aldredo.core.base.adapter.BaseListAdapter
import com.aldredo.services.R
import com.aldredo.services.di.ServicesScope
import com.aldredo.services.domain.dto.CategoryDto
import com.aldredo.services.presentation.ServicesViewModel
import com.aldredo.services.presentation.holder.CategoryHolder
import javax.inject.Inject

@ServicesScope
class CategoryAdapter @Inject constructor(private val viewModel: ServicesViewModel) :
    BaseListAdapter<CategoryDto>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false), viewModel, currentList
    )

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryDto>() {

            override fun areItemsTheSame(oldItem: CategoryDto, newItem: CategoryDto): Boolean {
                val b = oldItem.name == newItem.name
                return b
            }

            override fun areContentsTheSame(oldItem: CategoryDto, newItem: CategoryDto): Boolean {
                val b = oldItem == newItem
                return b
            }
        }
    }
}