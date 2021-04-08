package com.aldredo.profile_person.di

import com.aldredo.profile_person.data.api.HistoryApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServerApiModule {
    @Provides
    fun apiProvide(retrofit: Retrofit): HistoryApi = retrofit.create(HistoryApi::class.java)
}