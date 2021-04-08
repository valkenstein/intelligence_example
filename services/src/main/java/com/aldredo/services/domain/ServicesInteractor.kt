package com.aldredo.services.domain

import com.aldredo.services.data.repository.CategoriesPriceRepository
import com.aldredo.services.data.repository.PriceRepository
import javax.inject.Inject

class ServicesInteractor @Inject constructor(
    private val categoriesPriceRepository: CategoriesPriceRepository,
    private val priceRepository: PriceRepository
) {

    suspend fun getPrice() = priceRepository.requestPrice()

    suspend fun getCategoriesForPrice() = categoriesPriceRepository.requestCategoriesForPrice()

}