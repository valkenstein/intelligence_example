package com.aldredo.authorization.di

import com.aldredo.authorization.data.AuthoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
abstract class ServiceApiModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideApiAction(retrofit: Retrofit): AuthoApi = retrofit.create(AuthoApi::class.java)
    }

}