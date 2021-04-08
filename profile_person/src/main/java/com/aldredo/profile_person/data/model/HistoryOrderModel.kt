package com.aldredo.profile_person.data.model

data class HistoryOrderModel(
    val count: Int = 0,
    val results: ArrayList<ItemHistory>,
) {
    data class ItemHistory(
        val id: Int = 0,
        val zakaz_1c_num: String = "",
        val create_date: String = "",
        val zakaz_sum: String = "",
        val master_FIO: String = "",
        val client_1c_id: String = "",
    )
}

