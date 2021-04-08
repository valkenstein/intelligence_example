package com.aldredo.Intelligence.di

import com.aldredo.Intelligence.navigation.BottomNavManager
import com.aldredo.core.base.navigation.IBottomNavManager
import com.aldredo.core.base.scoupe.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {
    @Binds
    @ApplicationScope
    fun provideNavigation(nav: BottomNavManager): IBottomNavManager
}//HomeFragmentDirections