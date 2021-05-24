package com.enfiny.binancetracker.di

import android.content.Context
import androidx.room.Room
import com.enfiny.binancetracker.data.room.dao.MyPortfolioDao
import com.enfiny.binancetracker.data.room.db.MyPortfolioDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext app: Context): MyPortfolioDB {
        return Room.databaseBuilder(app, MyPortfolioDB::class.java, "MyPortfolio").build()
    }

    @Provides
    fun providedMyPortfolioDao(myPortfolioDB: MyPortfolioDB): MyPortfolioDao {
        return myPortfolioDB.myPortfolioDao()
    }
}