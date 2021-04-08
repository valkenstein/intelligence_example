package com.aldredo.authorization.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aldredo.authorization.di.AuthorizationScope
import com.aldredo.core.base.navigation.mediator.IAuthoCodeMediator
import com.aldredo.core.base.navigation.mediator.IAuthoMediator
import javax.inject.Inject

@AuthorizationScope
class Router @Inject constructor(
    private val authoMediator: IAuthoMediator,
    private val codeMediator: IAuthoCodeMediator
) {
    var fragment: Fragment? = null

    fun openCodeVerification() {
        if (fragment != null)
            authoMediator.openCodeScreen(fragment!!, Bundle())
    }

    fun openPersonRoom() {
        if (fragment != null)
            codeMediator.openProfilePerson(fragment!!, Bundle())
    }

    fun attachContext(fragment: Fragment) {
        this.fragment = fragment
    }

    fun detachContext() {
        fragment = null
    }

    fun finish() {
        fragment?.requireActivity()?.onBackPressed()
    }
}