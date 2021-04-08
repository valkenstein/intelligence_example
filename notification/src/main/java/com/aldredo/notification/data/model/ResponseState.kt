package com.aldredo.notification.data.model

sealed class ResponseState {
    data class Result(val notificationResponse: NotificationModel) : ResponseState()
    data class Error(val notificationError: String) : ResponseState()
    object Empty : ResponseState()
}

