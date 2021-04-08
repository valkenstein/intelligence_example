package com.aldredo.services.di

import androidx.fragment.app.Fragment
import com.aldredo.core.base.navigation.AppWithProvide
import com.aldredo.core.base.navigation.ProvideAppComponent
import com.aldredo.services.ServicesFragment
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@ServicesScope
@Component(dependencies = [ProvideAppComponent::class])
interface ServicesComponent {

    fun inject(servicesFragment: ServicesFragment)

    companion object {
        fun create(homeFragment: Fragment): ServicesComponent =
            DaggerServicesComponent
                .builder()
                .provideAppComponent((homeFragment.requireActivity().application as AppWithProvide).getFacade())
                .build()

    }

}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ServicesScope