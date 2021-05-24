package com.enfiny.binancetracker.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enfiny.binancetracker.data.room.dao.MyPortfolioDao
import com.enfiny.binancetracker.data.room.entity.MyPortfolio

@Database(entities = [MyPortfolio::class], version = 1, exportSchema = false)
abstract class MyPortfolioDB : RoomDatabase() {
    abstract fun myPortfolioDao(): MyPortfolioDao

    companion object {
        @Volatile
        private var instance: MyPortfolioDB? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MyPortfolioDB::class.java,
            "MyPortfolio.db"
        )
            .build()
    }
}