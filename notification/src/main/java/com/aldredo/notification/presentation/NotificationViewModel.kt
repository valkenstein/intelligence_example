package com.aldredo.notification.presentation

import androidx.lifecycle.MutableLiveData
import com.aldredo.notification.data.model.NotificationModel
import com.aldredo.notification.data.model.ResponseState
import com.aldredo.notification.data.repository.NotificationRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class NotificationViewModel @Inject constructor(private val repository: NotificationRepository) {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val notificationModel = MutableLiveData<List<NotificationModel.Result>>()
    fun getNotificationModel() = notificationModel

    fun init() = coroutineScope.launch {
        when (val result = getListNotificationAsync()) {
            is ResponseState.Result -> {
                notificationModel.postValue(result.notificationResponse.results)
            }
            is ResponseState.Error -> {

            }
        }
    }

    private suspend fun getListNotificationAsync() = withContext(Dispatchers.IO) {
        repository.getListNotification()
    }
}