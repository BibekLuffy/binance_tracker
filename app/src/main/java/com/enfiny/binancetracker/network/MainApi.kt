package com.enfiny.binancetracker.network

import com.enfiny.binancetracker.network.response.PriceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("ticker/price")
    suspend fun getPrice(@Query("symbol") symbol: String): PriceResponse

}