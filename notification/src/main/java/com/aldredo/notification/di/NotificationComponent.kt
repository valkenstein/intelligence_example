package com.aldredo.notification.di

import androidx.fragment.app.Fragment
import com.aldredo.core.base.navigation.AppWithProvide
import com.aldredo.core.base.navigation.ProvideAppComponent
import com.aldredo.notification.NotificationFragment
import dagger.Component

@Component(dependencies = [ProvideAppComponent::class])
interface NotificationComponent {

    fun inject(notificationComponent: NotificationFragment)

    companion object {
        fun create(homeFragment: Fragment): NotificationComponent =
            DaggerNotificationComponent
                .builder()
                .provideAppComponent((homeFragment.requireActivity().application as AppWithProvide).getFacade())
                .build()

    }
}