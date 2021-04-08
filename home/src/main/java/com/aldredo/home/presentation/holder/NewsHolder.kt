package com.aldredo.home.presentation.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.aldredo.core.base.holder.BaseHolder
import com.aldredo.home.R
import com.aldredo.home.data.dto.News
import com.aldredo.home.presentation.mvvm.Navigation
import com.bumptech.glide.Glide

class NewsHolder(
    private val view: View,
    private val currentList: MutableList<News>,
    private val navigation: Navigation
) : BaseHolder(view) {
    override fun bind() {
        view.findViewById<TextView>(R.id.date).text =
            currentList[bindingAdapterPosition].create_date
        view.findViewById<TextView>(R.id.title).text = currentList[bindingAdapterPosition].title
        view.findViewById<TextView>(R.id.description).text =
            currentList[bindingAdapterPosition].desc
        view.findViewById<ImageView>(R.id.image).apply {
            Glide.with(this).load(currentList[bindingAdapterPosition].image).into(this)
        }
        view.findViewById<CardView>(R.id.card_view_inner).setOnClickListener {
            navigation.openNewsCard(currentList, bindingAdapterPosition)
        }
    }
}