package com.aldredo.home.data

import androidx.lifecycle.MutableLiveData
import com.aldredo.home.data.api.ActionApi
import com.aldredo.home.data.api.NewsApi
import com.aldredo.home.data.dto.ResponseState
import com.aldredo.home.data.dto.ResultHome
import javax.inject.Inject

class Repository @Inject constructor(private val action: ActionApi, private val news: NewsApi) {
    suspend fun getDataHome(): MutableLiveData<ResponseState> {
        return try {
            val actions = action.getActionAll().results
            val news = news.getNews().results
            if (actions.isEmpty())
                MutableLiveData<ResponseState>().apply {
                    postValue(ResponseState.EmptyResponse)
                }
            else {
                MutableLiveData<ResponseState>()
                    .apply {
                        postValue(
                            ResponseState.Result(ArrayList<ResultHome>()
                                .apply {
                                    add(ResultHome(type = "news", news = news))
                                    add(ResultHome(type = "action", action = actions))
                                })
                        )
                    }
            }
        } catch (e: Exception) {
            MutableLiveData<ResponseState>()
                .apply {
                    postValue(ResponseState.Error(e.message))
                }
        }
    }
}