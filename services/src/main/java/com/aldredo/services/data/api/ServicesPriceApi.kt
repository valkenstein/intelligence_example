package com.aldredo.services.data.api

import com.aldredo.services.data.model.PriceModel
import retrofit2.http.GET

interface ServicesPriceApi {
    @GET("price/")
    suspend fun getPrice(): PriceModel
}