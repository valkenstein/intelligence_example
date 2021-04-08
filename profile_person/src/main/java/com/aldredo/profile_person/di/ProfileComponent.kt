package com.aldredo.profile_person.di

import androidx.fragment.app.Fragment
import com.aldredo.core.base.navigation.AppWithProvide
import com.aldredo.core.base.navigation.ProvideAppComponent
import com.aldredo.profile_person.presentation.fragment.OrderHistoryFragment
import com.aldredo.profile_person.presentation.fragment.ProfileFragment
import dagger.Component

@Component(
    dependencies = [ProvideAppComponent::class],
    modules = [ServerApiModule::class]
)
interface ProfileComponent {
    fun inject(profileFragment: ProfileFragment)

    fun inject(profileFragment: OrderHistoryFragment)

    companion object {
        fun create(homeFragment: Fragment): ProfileComponent =
            DaggerProfileComponent
                .builder()
                .provideAppComponent((homeFragment.requireActivity().application as AppWithProvide).getFacade())
                .build()

    }
}