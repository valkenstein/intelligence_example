package com.aldredo.profile_person.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldredo.profile_person.data.model.HistoryOrderModel
import com.aldredo.profile_person.data.model.HistoryResponseState
import com.aldredo.profile_person.domain.usecase.HistoryInteractor
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HistoryViewModel @Inject constructor(private val historyInteractor: HistoryInteractor) :
    ViewModel() {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val dataHistoryOrder = MutableLiveData<List<HistoryOrderModel.ItemHistory>>()
    fun getDataHistoryOrder() = dataHistoryOrder

    fun init() {
        coroutineScope.launch {
            when (val result = getOrderHistoryAsync()) {
                is HistoryResponseState.Result -> {
                    dataHistoryOrder.postValue(result.orderHistory.results)
                }
                else -> {
                }
            }
        }
    }

    private suspend fun getOrderHistoryAsync() = withContext(Dispatchers.IO) {
        historyInteractor.getOrderHistory()
    }

    fun stop() {
        coroutineScope.coroutineContext.cancelChildren()
    }
}