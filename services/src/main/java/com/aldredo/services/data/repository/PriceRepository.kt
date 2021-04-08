package com.aldredo.services.data.repository

import com.aldredo.services.data.api.ServicesPriceApi
import com.aldredo.services.data.mapping.MappingToDomain
import com.aldredo.services.data.model.PriceStateResponse
import retrofit2.Retrofit
import javax.inject.Inject

class PriceRepository @Inject constructor(private val retrofit: Retrofit) {
    suspend fun requestPrice() = try {
        val result = retrofit.create(ServicesPriceApi::class.java)
            .getPrice()
        PriceStateResponse.Result(MappingToDomain.mapPrice(result.results))
    } catch (e: Exception) {
        PriceStateResponse.Error(e.message)
    }
}

