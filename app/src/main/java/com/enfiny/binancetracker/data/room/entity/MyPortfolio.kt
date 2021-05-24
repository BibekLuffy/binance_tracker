package com.enfiny.binancetracker.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyPortfolio constructor(
    @ColumnInfo(name = "symbolB") val symbolB: String?,
    @ColumnInfo(name = "symbolS") val symbolS: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "priceOld") val priceOld: String?,
    @ColumnInfo(name = "quantity") val quantity: String?,
    @ColumnInfo(name = "costP") val costP: String?,
    @ColumnInfo(name = "sellP") val sellP: String?,
    @ColumnInfo(name = "fee") val fee: String?,
    @ColumnInfo(name = "isProfit") val isProfit: Boolean?,
    val pId: Int?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = pId ?: 0
}