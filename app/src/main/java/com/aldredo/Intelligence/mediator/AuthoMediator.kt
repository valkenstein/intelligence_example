package com.aldredo.Intelligence.mediator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aldredo.Intelligence.R
import com.aldredo.core.base.navigation.mediator.IAuthoMediator
import javax.inject.Inject

class AuthoMediator @Inject constructor() : IAuthoMediator {
    override fun openCodeScreen(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.action_autho_to_code, bundle)
    }
}