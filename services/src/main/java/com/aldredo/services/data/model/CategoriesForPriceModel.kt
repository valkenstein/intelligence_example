package com.aldredo.services.data.model

data class CategoriesForPriceModel(
    val count: String,
    val next: String,
    val previous: String,
    val results: List<Result>,
) {
    data class Result(
        val id: String?,
        val id_1c: String,
        val value: String?,
        val color: String
    )
}