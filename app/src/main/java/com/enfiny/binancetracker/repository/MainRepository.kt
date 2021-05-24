package com.enfiny.binancetracker.repository

import com.enfiny.binancetracker.data.room.dao.MyPortfolioDao
import com.enfiny.binancetracker.data.room.entity.MyPortfolio
import com.enfiny.binancetracker.network.MainApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: MainApi,
    private val dao: MyPortfolioDao
) : BaseRepository() {

    val getMyPortfolio: Flow<List<MyPortfolio>> = dao.getAll()

    suspend fun insert(myPortfolio: MyPortfolio) = withContext(Dispatchers.IO) {
        dao.insert(myPortfolio)
    }

    suspend fun update(myPortfolio: MyPortfolio) = withContext(Dispatchers.IO) {
        dao.update(myPortfolio)
    }

    suspend fun getPrice(symbol: String) = safeApiCall {
        api.getPrice(symbol)
    }

    suspend fun delete(pId: Int) = withContext(Dispatchers.IO) {
        dao.delete(pId)
    }
}