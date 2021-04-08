package com.aldredo.profile_person.domain.usecase

import com.aldredo.profile_person.data.repository.HistoryRepository
import javax.inject.Inject

class HistoryInteractor @Inject constructor(private val repository: HistoryRepository) {
    suspend fun getOrderHistory() =
        repository.getHistoryApi()

}