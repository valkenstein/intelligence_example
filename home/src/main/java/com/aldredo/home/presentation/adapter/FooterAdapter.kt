package com.aldredo.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.adapter.BaseRecyclerAdapter
import com.aldredo.home.R
import com.aldredo.core.base.holder.EmptyHolder

class FooterAdapter : BaseRecyclerAdapter<Any>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
        return EmptyHolder(inflateView)
    }
}