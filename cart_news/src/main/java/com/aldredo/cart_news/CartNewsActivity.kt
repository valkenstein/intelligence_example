package com.aldredo.cart_news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import java.lang.Exception

class CartNewsActivity : Fragment(R.layout.item_head_news) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsListJson = arguments?.getString(ARG_NEWS) ?: ""
        val position = arguments?.getInt(ARG_POSITION, 0) ?: 0
        try {
            Gson().fromJson(newsListJson, Array<NewsAll>::class.java).toList().apply {
                NewsHolder(view, this, position).apply {
                    bind()
                    bindOtherNews()
                }
            }
        } catch (e: Exception) {
            print(e.message)
        }
    }

    companion object {
        const val ARG_NEWS = "newsAll"
        const val ARG_POSITION = "position"
    }
}