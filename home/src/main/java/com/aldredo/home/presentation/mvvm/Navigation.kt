package com.aldredo.home.presentation.mvvm

import com.aldredo.home.data.dto.Actions
import com.aldredo.home.data.dto.News

interface Navigation {
    fun openNewsCard(currentList: MutableList<News>, bindingAdapterPosition: Int)

    fun openActionCard(currentList: MutableList<Actions>, bindingAdapterPosition: Int)
}