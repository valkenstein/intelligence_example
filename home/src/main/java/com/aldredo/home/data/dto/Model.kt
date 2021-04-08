package com.aldredo.home.data.dto

data class ResultHome(
    val type: String? = "",
    val action: List<Actions> = listOf(),
    val news: List<News> = listOf()
)

data class ActionResponse(
    val count: String? = "",
    val results: ArrayList<Actions> = ArrayList(),
)

data class NewsResponse(
    val count: String? = "",
    val results: ArrayList<News> = ArrayList(),
)

data class Actions(
    val id: Int = 0,
    val trumb: String? = "",
    val image: String? = "",
    val title: String? = "",
    val create_date: String? = "",
    val is_active: Boolean = false,
    val is_banner: Boolean = false
)

data class News(
    val image: String? = "",
    val title: String? = "",
    val desc: String? = "",
    val create_date: String? = "",
    val id: Int = 0
)
