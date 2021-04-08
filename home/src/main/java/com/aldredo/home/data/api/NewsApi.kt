package com.aldredo.home.data.api

import com.aldredo.home.data.dto.News
import com.aldredo.home.data.dto.NewsResponse
import retrofit2.http.GET

interface NewsApi {
    @GET("news/")
    suspend fun getNews(): NewsResponse
}