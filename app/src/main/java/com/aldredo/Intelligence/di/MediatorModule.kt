package com.aldredo.Intelligence.di

import com.aldredo.Intelligence.mediator.AuthoCodeMediator
import com.aldredo.Intelligence.mediator.AuthoMediator
import com.aldredo.Intelligence.mediator.HomeMediator
import com.aldredo.Intelligence.mediator.ProfilePersonMediator
import com.aldredo.core.base.navigation.mediator.IAuthoCodeMediator
import com.aldredo.core.base.navigation.mediator.IAuthoMediator
import com.aldredo.core.base.navigation.mediator.IHomeMeidator
import com.aldredo.core.base.navigation.mediator.IProfilePersonMediator
import com.aldredo.core.base.scoupe.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
interface MediatorModule {
    @Binds
    @ApplicationScope
    fun provideAuthoMediator(authoMediator: AuthoMediator): IAuthoMediator

    @Binds
    @ApplicationScope
    fun provideHomeMediator(homeMediator: HomeMediator): IHomeMeidator

    @Binds
    @ApplicationScope
    fun provideauthoCodeMediator(authoCodeMediator: AuthoCodeMediator): IAuthoCodeMediator

    @Binds
    @ApplicationScope
    fun provideProfileMediator(profilePersonMediator: ProfilePersonMediator): IProfilePersonMediator
}