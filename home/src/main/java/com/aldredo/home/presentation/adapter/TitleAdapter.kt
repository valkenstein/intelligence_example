package com.aldredo.home.presentation.adapter

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.R
import com.aldredo.core.base.adapter.BaseRecyclerAdapter
import com.aldredo.core.base.holder.EmptyHolder

class TitleAdapter : BaseRecyclerAdapter<Any>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EmptyHolder(TextView(parent.context).apply {
            text = "Новости"
            setTextColor(Color.BLACK)
            typeface = ResourcesCompat.getFont(parent.context, R.font.roboto_medium)
            textSize = 20f
            setPadding(30, 0, 0, 0)
        })
    }
}