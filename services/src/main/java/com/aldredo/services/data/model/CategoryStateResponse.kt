package com.aldredo.services.data.model

import com.aldredo.services.domain.dto.CategoryDto

sealed class CategoryStateResponse {
    data class Error(val message: String?) : CategoryStateResponse()
    data class Result(val result: List<CategoryDto>): CategoryStateResponse()
    object EmptyResponse: CategoryStateResponse()
}
