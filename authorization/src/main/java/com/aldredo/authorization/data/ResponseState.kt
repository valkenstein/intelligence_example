package com.aldredo.authorization.data

import com.aldredo.core.base.util.TokenModel

sealed class ResponseState {
    data class Error(val message: String?) : ResponseState()
    data class Result(val result: TokenModel): ResponseState()
    object EmptyResponse: ResponseState()
}