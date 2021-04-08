package com.aldredo.services.data.model

data class PriceModel(
    val count: String,
    val next: String,
    val previous: String,
    val results: ArrayList<Result>,
) {
    data class Result(
        val id: String = "",
        val title: String = "",
        val cost: String = "",
        val category: String = ""
    )
}