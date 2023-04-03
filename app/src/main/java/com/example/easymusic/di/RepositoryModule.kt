package com.example.easymusic.di

import com.example.easymusic.core_data.repository.charts.ChartsRepository
import com.example.easymusic.core_data.repository.charts.ChartsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindChartsRepository(chartsRepositoryImpl: ChartsRepositoryImpl): ChartsRepository
}