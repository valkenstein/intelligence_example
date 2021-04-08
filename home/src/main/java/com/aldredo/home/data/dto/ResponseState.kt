package com.aldredo.home.data.dto

sealed class ResponseState {
    data class Error(val message: String?) : ResponseState()
    data class Result(val result: List<ResultHome>): ResponseState()
    object EmptyResponse: ResponseState()
}