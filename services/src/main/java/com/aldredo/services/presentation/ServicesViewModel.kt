package com.aldredo.services.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldredo.services.data.model.CategoryStateResponse
import com.aldredo.services.data.model.PriceStateResponse
import com.aldredo.services.di.ServicesScope
import com.aldredo.services.domain.ServicesInteractor
import com.aldredo.services.domain.dto.CategoryDto
import com.aldredo.services.domain.dto.PriceDto
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

@ServicesScope
class ServicesViewModel @Inject constructor(private val servicesInteractor: ServicesInteractor) :
    ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val allPriceData = MutableLiveData<HashMap<String, ArrayList<PriceDto>>>()
    private val currentPriceData = MutableLiveData<List<PriceDto>>()
    private val categoryData = MediatorLiveData<List<CategoryDto>>()
    private val errorMessages = MutableLiveData<String>()

    fun getCurrentPriceData(): LiveData<List<PriceDto>> = currentPriceData
    fun getCategoryData(): MediatorLiveData<List<CategoryDto>> = categoryData
    fun getErrorMessages(): LiveData<String> = errorMessages

    fun init() {
        getCategoriesWithPrices()
    }

    private fun getCategoriesWithPrices() = scope.launch {
        val price = getPriceAsync()
        val category = getCategoriesForPriceAsync()
        processingResult(price.await(), category.await())
    }

    private fun processingResult(price: PriceStateResponse, category: CategoryStateResponse) {
        when {
            price is PriceStateResponse.Result && category is CategoryStateResponse.Result -> {
                allPriceData.postValue(price.result)
                categoryData.postValue(category.result)
                if (price.result.containsKey(category.result[0].id)) {
                    category.result[0].active = true
                    currentPriceData.postValue(price.result[category.result[0].id])
                } else errorMessages.postValue("коллизия на сервере")
            }
            price is PriceStateResponse.Error -> {
                errorMessages.postValue(price.message)
            }
            category is CategoryStateResponse.Error -> {
                errorMessages.postValue(category.message)
            }
            else -> {
                errorMessages.postValue("неизвестная ошибка")
            }
        }
    }

    private suspend fun getPriceAsync() = scope.async(Dispatchers.IO) {
        servicesInteractor.getPrice()
    }

    private suspend fun getCategoriesForPriceAsync() = scope.async(Dispatchers.IO) {
        servicesInteractor.getCategoriesForPrice()
    }

    fun selectedCategory(id: String) {
        categoryData.value?.forEach {
            it.active = it.id == id
        }
        categoryData.postValue(categoryData.value)
        allPriceData.value?.apply {
            if (containsKey(id)) {
                currentPriceData.postValue(allPriceData.value.let {
                    it?.get(id)
                })
            }
        }
    }

    fun clear() {
        scope.coroutineContext.cancelChildren()
    }
}