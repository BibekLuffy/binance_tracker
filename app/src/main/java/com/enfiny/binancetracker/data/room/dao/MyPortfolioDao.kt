package com.enfiny.binancetracker.data.room.dao

import androidx.room.*
import com.enfiny.binancetracker.data.room.entity.MyPortfolio
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPortfolioDao {
    @Query("SELECT * FROM myportfolio ORDER BY id ASC")
    fun getAll(): Flow<List<MyPortfolio>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myportfolios: MyPortfolio)

    @Query("DELETE FROM myportfolio WHERE id = :pId")
    suspend fun delete(pId: Int)

    @Update
    suspend fun update(myportfolios: MyPortfolio)
}