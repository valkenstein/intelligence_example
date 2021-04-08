package com.aldredo.services.data.api

import com.aldredo.services.data.model.CategoriesForPriceModel
import retrofit2.http.GET

interface ServicesCategoriesPriceApi {
    @GET("price_categories/")
    suspend fun getCategoriesPrice(): CategoriesForPriceModel
}