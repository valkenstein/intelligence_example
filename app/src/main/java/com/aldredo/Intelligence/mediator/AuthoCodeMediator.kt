package com.aldredo.Intelligence.mediator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aldredo.Intelligence.R
import com.aldredo.core.base.navigation.mediator.IAuthoCodeMediator
import javax.inject.Inject

class AuthoCodeMediator @Inject constructor() : IAuthoCodeMediator {
    override fun openProfilePerson(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.profile_person, bundle)
    }
}