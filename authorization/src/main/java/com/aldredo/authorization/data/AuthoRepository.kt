package com.aldredo.authorization.data

import java.lang.Exception
import javax.inject.Inject

class AuthoRepository @Inject constructor(private val api: AuthoApi) {
    suspend fun putDataForAuthorization(number: String, code: String): ResponseState {
        return try {
            val token = api.putAuthoData(HashMap<String, String>().apply {
                put("username", number)
                put("password", code)
            })

            if (token.token.isEmpty()) {
                ResponseState.EmptyResponse
            } else {
                ResponseState.Result(token)
            }
        } catch (e: Exception) {
            ResponseState.Error(e.message)
        }
    }
}