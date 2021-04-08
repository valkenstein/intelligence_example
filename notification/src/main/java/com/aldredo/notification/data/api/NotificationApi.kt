package com.aldredo.notification.data.api

import com.aldredo.notification.data.model.NotificationModel
import retrofit2.http.GET

interface NotificationApi {
    @GET("orders/")
    suspend fun getOrderHistory(): NotificationModel
}