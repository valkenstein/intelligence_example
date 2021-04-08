package com.aldredo.core.base.util

interface IManagerToken {

    fun getToken(): TokenModel?

    fun saveToken(token: TokenModel)

    fun unsubscribe(listener: ObserverChangeToken)

    fun removeToken()

    fun subscribe(listener: ObserverChangeToken)

    fun checkStateAuthorized(): Boolean
}