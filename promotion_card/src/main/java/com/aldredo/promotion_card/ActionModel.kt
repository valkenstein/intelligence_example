package com.aldredo.promotion_card

data class ActionModel(
    val trumb: String? = "",
    val image: String? = "",
    val title: String? = "",
    val create_date: String? = "",
    val active: Boolean = false,
    val is_banner: Boolean = false,
    val id: Int = 0
)