package com.aldredo.authorization.presentation

import com.aldredo.authorization.data.AuthoRepository
import com.aldredo.authorization.data.ResponseState
import com.aldredo.authorization.di.AuthorizationScope
import com.aldredo.authorization.navigation.Router
import com.aldredo.core.base.util.IManagerToken
import kotlinx.coroutines.*
import javax.inject.Inject

@AuthorizationScope
class Presenter @Inject constructor(
    private val managerToken: IManagerToken,
    private val router: Router
) {
    @Inject
    lateinit var authoRepository: AuthoRepository
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun putData(number: String, code: String) = scope.launch(Dispatchers.Main) {
        when (val token = getToken(number, code)) {
            is ResponseState.Result -> {
                managerToken.saveToken(token.result)
                router.openPersonRoom()
            }
            is ResponseState.EmptyResponse -> {
                TODO()
            }
            is ResponseState.Error -> {
                TODO()
            }
        }
    }

    private suspend fun getToken(number: String, code: String) =
        withContext(scope.coroutineContext + Dispatchers.IO) {
            authoRepository.putDataForAuthorization(number, code)
        }
}