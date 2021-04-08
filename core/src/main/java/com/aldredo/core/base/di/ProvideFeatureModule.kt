package com.aldredo.core.base.di

import com.aldredo.core.base.util.IManagerToken
import retrofit2.Retrofit

interface ProvideFeatureModule {
    fun getRetrofit(): Retrofit

    fun getManagerToken(): IManagerToken
}