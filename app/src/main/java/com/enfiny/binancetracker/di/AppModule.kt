package com.enfiny.binancetracker.di

import android.content.Context
import androidx.room.Room
import com.enfiny.binancetracker.MyApplication
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

object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }

}