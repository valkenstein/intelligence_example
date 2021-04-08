package com.aldredo.notification.data.model

data class NotificationModel(
    val count: String,
    val next: String,
    val previous: String,
    val results: List<Result>,
) {
    data class Result(
        val id: String,
        val id_1c: String,
        val value: String,
        val color: String
    )
}
