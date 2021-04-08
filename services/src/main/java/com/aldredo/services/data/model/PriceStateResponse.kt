package com.aldredo.services.data.model

import com.aldredo.services.domain.dto.PriceDto

sealed class PriceStateResponse {
    data class Error(val message: String?) : PriceStateResponse()
    data class Result(val result: HashMap<String, ArrayList<PriceDto>>) : PriceStateResponse()
    object EmptyResponse : PriceStateResponse()
}