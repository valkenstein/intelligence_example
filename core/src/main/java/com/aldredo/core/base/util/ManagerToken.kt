package com.aldredo.core.base.util


internal class ManagerToken : IManagerToken {
    private var token: TokenModel? = null
    private val listeners = ArrayList<ObserverChangeToken>()
    override fun getToken(): TokenModel? {
        return if (token != null) token
        else token
    }

    override fun subscribe(listener: ObserverChangeToken) {
        listeners.add(listener)
    }

    override fun unsubscribe(listener: ObserverChangeToken) {
        listeners.remove(listener)
    }

    override fun saveToken(token: TokenModel) {
        this.token = token
        fireListener()
    }

    private fun fireListener() {
        for (item in listeners)
            item.tokenChange(checkStateAuthorized())
    }

    override fun removeToken() {
        token = null
        fireListener()
    }

    override fun checkStateAuthorized() = token?.token?.isNotEmpty() ?: false
}