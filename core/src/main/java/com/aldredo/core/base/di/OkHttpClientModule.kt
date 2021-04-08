package com.aldredo.core.base.di

import com.aldredo.core.base.interceptor.ApiInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

@Module
class OkHttpClientModule {
    @Provides
    @Singleton
    fun provideDispatcher() = Dispatcher().apply { maxRequests = 1 }

    @Provides
    @Singleton
    fun provideHttpClientAuth(
        dispatcher: Dispatcher,
        interceptor: ApiInterceptor,
        verify: HostnameVerifier
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .dispatcher(dispatcher)
            .hostnameVerifier(verify)
            .build()
    }

    @Provides
    @Singleton
    fun provideVerifierHost() = HostnameVerifier { p0, p1 -> true } //TODO УБРАТЬ

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClientAuth: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("hidden")
            .client(okHttpClientAuth)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


}
