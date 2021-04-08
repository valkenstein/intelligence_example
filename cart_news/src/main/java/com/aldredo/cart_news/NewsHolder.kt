package com.aldredo.cart_news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.aldredo.core.base.holder.BaseHolder
import com.bumptech.glide.Glide

class NewsHolder(
    private val view: View,
    private val currentList: List<NewsAll>,
    private val currentPosition: Int
) : BaseHolder(view) {
    override fun bind() {
        view.findViewById<TextView>(R.id.date).text =
            currentList[currentPosition].create_date
        view.findViewById<TextView>(R.id.title).text = currentList[currentPosition].title
        view.findViewById<TextView>(R.id.description).text =
            currentList[currentPosition].desc
        view.findViewById<ImageView>(R.id.image).apply {
            Glide.with(this).load(currentList[currentPosition].image).into(this)
        }
    }

    fun bindOtherNews() {
        val body = view.findViewById<ViewGroup>(R.id.body)
        for (i in 0 until currentList.count()) {
            LayoutInflater.from(view.context).inflate(R.layout.item_similar_news, body, false)
                .apply {
                    body.addView(this)
                    NewsHolder(this, currentList, i).bind()
                }
        }
    }
}