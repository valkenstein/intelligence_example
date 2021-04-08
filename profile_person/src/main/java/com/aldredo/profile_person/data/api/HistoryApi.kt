package com.aldredo.profile_person.data.api

import com.aldredo.profile_person.data.model.HistoryOrderModel
import retrofit2.http.GET

interface HistoryApi {
    @GET("order_status/")
    suspend fun getOrderHistory(): HistoryOrderModel
}