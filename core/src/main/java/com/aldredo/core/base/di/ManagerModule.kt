package com.aldredo.core.base.di

import com.aldredo.core.base.util.IManagerToken
import com.aldredo.core.base.util.ManagerToken
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ManagerModule {
    @Provides
    @Singleton
    fun provideManagerToken(): IManagerToken {
        return ManagerToken()
    }
}