package com.aldredo.services.presentation.holder

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.aldredo.core.base.holder.BaseHolder
import com.aldredo.services.R
import com.aldredo.services.domain.dto.CategoryDto
import com.aldredo.services.presentation.ServicesViewModel

class CategoryHolder(
    private val view: View,
    viewModel: ServicesViewModel,
    private val currentList: MutableList<CategoryDto>
) : BaseHolder(view) {
    private var title: TextView
    private var line: View

    init {
        view.setOnClickListener {
            viewModel.selectedCategory(currentList[bindingAdapterPosition].id)
        }
        title = view.findViewById(R.id.title)
        line = view.findViewById(R.id.line1)
    }

    private fun setColor(parent: ViewGroup, colorText: Int, colorView: Int) {
        line.setBackgroundColor(ContextCompat.getColor(parent.context, colorText))
        title.setTextColor(colorView)
    }


    override fun bind() {
        val parent = (view as ViewGroup)
        if (currentList[bindingAdapterPosition].active) {
            setColor(parent, R.color.colorDarkGray, Color.BLACK)
        } else {
            setColor(parent, R.color.colorGray, R.color.colorGray)
        }
    }
}