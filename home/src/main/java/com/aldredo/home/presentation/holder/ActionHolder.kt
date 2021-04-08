package com.aldredo.home.presentation.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.aldredo.core.base.holder.BaseHolder
import com.aldredo.home.R
import com.aldredo.home.data.dto.Actions
import com.aldredo.home.presentation.mvvm.Navigation
import com.bumptech.glide.Glide

class ActionHolder(
    private val view: View,
    private val currentList: MutableList<Actions>,
    private val navigation: Navigation
) :
    BaseHolder(view) {
    override fun bind() {
        view.findViewById<TextView>(R.id.title).text = currentList[bindingAdapterPosition].title
        view.findViewById<ImageView>(R.id.image).apply {
            Glide.with(this).load(currentList[bindingAdapterPosition].image).into(this)
        }
        view.setOnClickListener {
            navigation.openActionCard(currentList, bindingAdapterPosition)
        }
    }
}