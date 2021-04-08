package com.aldredo.services.data.repository

import com.aldredo.services.data.api.ServicesCategoriesPriceApi
import com.aldredo.services.data.mapping.MappingToDomain
import com.aldredo.services.data.model.CategoryStateResponse
import retrofit2.Retrofit
import javax.inject.Inject

class CategoriesPriceRepository @Inject constructor(private val retrofit: Retrofit) {
    suspend fun requestCategoriesForPrice() = try {
        val result = retrofit.create(ServicesCategoriesPriceApi::class.java)
            .getCategoriesPrice()
        CategoryStateResponse.Result(MappingToDomain.mapCategory(result.results))
    } catch (e: Exception) {
        CategoryStateResponse.Error(e.message)
    }
}
