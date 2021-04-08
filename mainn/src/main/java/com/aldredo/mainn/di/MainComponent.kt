package com.aldredo.mainn.di

import com.aldredo.core.base.navigation.AppWithProvide
import com.aldredo.core.base.navigation.ProvideAppComponent
import com.aldredo.mainn.MainActivity
import dagger.Component

@Component(dependencies = [ProvideAppComponent::class])
interface MainComponent {
    fun inject(app: MainActivity)

    companion object {
        fun create(activity: MainActivity) {
            val app = (activity.application as AppWithProvide).getFacade()
            DaggerMainComponent
                .builder()
                .provideAppComponent(app)
                .build()
                .inject(activity)
        }
    }
}