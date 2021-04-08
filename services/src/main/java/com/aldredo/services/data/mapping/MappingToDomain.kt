package com.aldredo.services.data.mapping

import com.aldredo.services.data.model.CategoriesForPriceModel
import com.aldredo.services.data.model.PriceModel
import com.aldredo.services.domain.dto.CategoryDto
import com.aldredo.services.domain.dto.PriceDto

object MappingToDomain {
    fun mapPrice(priceModel: ArrayList<PriceModel.Result>) =
        HashMap<String, ArrayList<PriceDto>>().apply {
            priceModel.forEach {
                if (containsKey(it.category))
                    get(it.category)?.add(PriceDto(it.cost, it.title))
                else {
                    put(it.category, ArrayList<PriceDto>().apply {
                        add(PriceDto(it.cost, it.title))
                    })
                }
            }
        }

    fun mapCategory(categoryModel: List<CategoriesForPriceModel.Result>) =
        ArrayList<CategoryDto>().apply {
            categoryModel.forEach {
                add(CategoryDto(it.id ?: "", it.value ?: "", false))
            }
        }
}