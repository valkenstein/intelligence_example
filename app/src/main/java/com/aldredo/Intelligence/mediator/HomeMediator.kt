package com.aldredo.Intelligence.mediator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aldredo.Intelligence.R
import com.aldredo.core.base.navigation.mediator.IHomeMeidator
import javax.inject.Inject

class HomeMediator @Inject constructor() : IHomeMeidator {
    override fun openNewsCard(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.action_home_to_card_news, bundle)
    }

    override fun openActionCard(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.action_home_to_card_action, bundle)
    }
}