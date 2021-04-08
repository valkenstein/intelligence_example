package com.aldredo.core.base.interceptor

import com.aldredo.core.base.util.IManagerToken
import com.aldredo.core.base.util.ManagerToken
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(
    private val managerToken: IManagerToken
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        synchronized(this) {
            return chain.proceed(createRequest(chain))
        }
    }

    private fun createRequest(chain: Interceptor.Chain) =
        chain.request().run {
            val urlWithApiKey = url
                .newBuilder()
                .build()

            newBuilder()
                .header("Authorization", "Bearer ${managerToken.getToken()}")
                .url(urlWithApiKey)
                .build()
        }
}
