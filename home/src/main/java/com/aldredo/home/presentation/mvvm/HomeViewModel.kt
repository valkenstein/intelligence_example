package com.aldredo.home.presentation.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldredo.home.data.Repository
import com.aldredo.home.data.dto.Actions
import com.aldredo.home.data.dto.News
import com.aldredo.home.data.dto.ResponseState
import com.aldredo.home.data.dto.ResultHome
import com.aldredo.home.navigation.Router
import kotlinx.coroutines.*

class HomeViewModel(private val repository: Repository, private val router: Router) : ViewModel(),
    Navigation {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val dataHome = MutableLiveData<List<ResultHome>>()
    private val messageError: MutableLiveData<String> = MutableLiveData()

    fun getHomeData() = dataHome

    fun getErrorMessage() = messageError

    fun getActionOrNews() = scope.launch(Dispatchers.Main) {
        when (val response = loadActionOrNewsAsync().value) {
            is ResponseState.Result -> {
                dataHome.postValue(response.result)
            }

            is ResponseState.EmptyResponse -> {
                messageError.postValue("пустые данные")
            }

            is ResponseState.Error -> {
                messageError.postValue("ошибка сервера")
            }

            else -> {
                messageError.postValue("неизвестная ошибка")
            }
        }
    }

    override fun openNewsCard(currentList: MutableList<News>, bindingAdapterPosition: Int) {
        router.openNewsCard(currentList, bindingAdapterPosition)
    }

    override fun openActionCard(currentList: MutableList<Actions>, bindingAdapterPosition: Int) {
        router.openActionCard(currentList, bindingAdapterPosition)
    }

    private suspend fun loadActionOrNewsAsync() = withContext(Dispatchers.IO) {
        repository.getDataHome()
    }

    fun stop() {
        scope.coroutineContext.cancelChildren()
    }
}