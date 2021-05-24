package com.enfiny.binancetracker.di

import com.enfiny.binancetracker.network.MainApi
import com.enfiny.binancetracker.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {
    @Singleton
    @Provides
    fun provideMainApi(
        remoteDataSource: RemoteDataSource
    ): MainApi {
        return remoteDataSource.buildApi(MainApi::class.java)
    }
}