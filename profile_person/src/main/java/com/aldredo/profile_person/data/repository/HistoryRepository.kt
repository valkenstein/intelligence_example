package com.aldredo.profile_person.data.repository

import com.aldredo.profile_person.data.api.HistoryApi
import com.aldredo.profile_person.data.model.HistoryResponseState
import java.lang.Exception
import javax.inject.Inject

class HistoryRepository @Inject constructor(private val apiHistory: HistoryApi) {
    suspend fun getHistoryApi(): HistoryResponseState {
        return try {
            val result = apiHistory.getOrderHistory()
            return if (result.results.isEmpty())
                HistoryResponseState.Empty
            else
                HistoryResponseState.Result(result)

        } catch (e: Exception) {
            HistoryResponseState.Error(e.message.toString())
        }
    }
}