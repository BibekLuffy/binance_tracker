package com.enfiny.binancetracker.network

import com.enfiny.binancetracker.network.response.ErrorMessageResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<out T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        try {
                            val gson = Gson()
                            val error = gson.fromJson(
                                throwable.response()?.errorBody()?.charStream(),
                                ErrorMessageResponse::class.java
                            )
                            Resource.Failure(
                                false, throwable.code(), null
                            )
                        } catch (e: Exception) {
                            Resource.Failure(false, throwable.code(), null)
                        }
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}