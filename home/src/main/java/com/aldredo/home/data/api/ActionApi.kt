package com.aldredo.home.data.api

import com.aldredo.home.data.dto.ActionResponse
import retrofit2.http.GET

interface ActionApi {
    @GET("promo/")
    suspend fun getActionAll(): ActionResponse
}