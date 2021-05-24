package com.enfiny.binancetracker.di

import com.enfiny.binancetracker.data.room.dao.MyPortfolioDao
import com.enfiny.binancetracker.network.MainApi
import com.enfiny.binancetracker.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthRepository(api: MainApi, dao: MyPortfolioDao): MainRepository {
        return MainRepository(
            api, dao
        )
    }
}