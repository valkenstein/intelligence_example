package com.aldredo.home.di

import com.aldredo.home.data.api.ActionApi
import com.aldredo.home.data.api.NewsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class ServiceApiModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideApiAction(retrofit: Retrofit): ActionApi =
            retrofit.create(ActionApi::class.java)

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideApiNews(retrofit: Retrofit): NewsApi =
            retrofit.create(NewsApi::class.java)
    }
}