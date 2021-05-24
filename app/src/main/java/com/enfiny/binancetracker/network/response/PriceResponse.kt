package com.enfiny.binancetracker.network.response

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("price")
    var price: String?,
    @SerializedName("symbol")
    var symbol: String?
)