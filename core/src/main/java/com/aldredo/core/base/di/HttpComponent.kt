package com.aldredo.core.base.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [OkHttpClientModule::class, ManagerModule::class])
interface HttpComponent : ProvideFeatureModule {

    companion object {
        fun create(): HttpComponent =
            DaggerHttpComponent
                .builder()
                .build()
    }
}