package com.aldredo.home.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aldredo.core.base.navigation.mediator.CardNewsMediator
import com.aldredo.core.base.navigation.mediator.CardPromotionMediator
import com.aldredo.core.base.navigation.mediator.IHomeMeidator
import com.aldredo.home.data.dto.Actions
import com.aldredo.home.data.dto.News
import com.aldredo.home.di.ActivityScope
import com.google.gson.Gson
import javax.inject.Inject

@ActivityScope
class Router @Inject constructor(private val homeMediator: IHomeMeidator) {
    var context: Fragment? = null
    fun openNewsCard(currentList: MutableList<News>, bindingAdapterPosition: Int) {
        if (context != null)
            homeMediator.openNewsCard(context!!, Bundle().apply {
                putString(CardNewsMediator.ARG_NEWS, Gson().toJson(currentList))
                putInt(CardNewsMediator.ARG_POSITION, bindingAdapterPosition)
            })

    }

    fun attachContext(context: Fragment) {
        this.context = context
    }

    fun detachContext() {
        context = null
    }

    fun openActionCard(currentList: MutableList<Actions>, bindingAdapterPosition: Int) {
        if (context != null)
            homeMediator.openActionCard(context!!, Bundle().apply {
                putString(CardPromotionMediator.ARG_ACTIONS, Gson().toJson(currentList))
                putInt(CardPromotionMediator.ARG_POSITION, bindingAdapterPosition)
            })
    }
}