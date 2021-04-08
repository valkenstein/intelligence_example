package com.aldredo.notification.data.repository

import com.aldredo.notification.data.api.NotificationApi
import com.aldredo.notification.data.model.ResponseState
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class NotificationRepository @Inject constructor(private val retrofit: Retrofit) {

    suspend fun getListNotification(): ResponseState {
        return try {
            val result = retrofit.create(NotificationApi::class.java).getOrderHistory()
            ResponseState.Result(result)
        } catch (e: Exception) {
            ResponseState.Error(e.message.toString())
        }
    }
}