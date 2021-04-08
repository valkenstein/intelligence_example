package com.aldredo.Intelligence.mediator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aldredo.Intelligence.R
import com.aldredo.core.base.navigation.mediator.IProfilePersonMediator
import javax.inject.Inject

class ProfilePersonMediator @Inject constructor() : IProfilePersonMediator {
    override fun openEditPerson(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.action_profile_person_to_profile_edit, bundle)
    }

    override fun openAuthorization(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.action_profile_person_to_authorization, bundle)
    }

    override fun openOrderHistory(fragment: Fragment, bundle: Bundle) {
        fragment.findNavController().navigate(R.id.action_profile_person_to_history_order, bundle)
    }
}