package com.aldredo.authorization.di

import com.aldredo.authorization.presentation.activity.AuthorizationCodeFragment
import com.aldredo.authorization.presentation.activity.AuthorizationFragment
import com.aldredo.core.base.navigation.AppWithProvide
import com.aldredo.core.base.navigation.ProvideAppComponent
import com.aldredo.core.base.scoupe.ApplicationScope
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton


@AuthorizationScope
@Component(
    dependencies = [ProvideAppComponent::class],
    modules = [ServiceApiModule::class]
)
interface AuthoComponent {
    fun inject(app: AuthorizationCodeFragment)

    fun inject(app: AuthorizationFragment)

    companion object {
        fun create(fragment: AuthorizationCodeFragment) {
            val app = (fragment.requireActivity().application as AppWithProvide).getFacade()
            DaggerAuthoComponent
                .builder()
                .provideAppComponent(app)
                .build()!!
                .inject(fragment)
        }

        fun create(fragment: AuthorizationFragment) {
            val app = (fragment.requireActivity().application as AppWithProvide).getFacade()
            DaggerAuthoComponent
                .builder()
                .provideAppComponent(app)
                .build()!!
                .inject(fragment)
        }
    }
}


