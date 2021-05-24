package com.enfiny.binancetracker.network.response

import com.google.gson.annotations.SerializedName

data class ErrorMessageResponse(
    @SerializedName("message")
    val message: String?
)
