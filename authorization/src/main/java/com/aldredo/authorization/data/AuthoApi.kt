package com.aldredo.authorization.data

import com.aldredo.core.base.util.TokenModel
import retrofit2.http.*


interface AuthoApi {
    @POST("login/")
    suspend fun putAuthoData(@Body() username: HashMap<String, String>): TokenModel
}