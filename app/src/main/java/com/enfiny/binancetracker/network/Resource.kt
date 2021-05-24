package com.enfiny.binancetracker.network

import com.enfiny.binancetracker.network.response.ErrorMessageResponse

sealed class Resource<T> {
    data class Success<T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ErrorMessageResponse?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}