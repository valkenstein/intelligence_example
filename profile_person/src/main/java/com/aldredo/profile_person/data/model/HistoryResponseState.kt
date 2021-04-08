package com.aldredo.profile_person.data.model

sealed class HistoryResponseState {
    data class Result(val orderHistory: HistoryOrderModel) : HistoryResponseState()
    data class Error(val orderHistory: String) : HistoryResponseState()
    object Empty : HistoryResponseState()
}