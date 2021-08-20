package com.example.themovie.di


import com.example.themovie.repository.HomeDataImpl
import com.example.themovie.repository.HomeDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideHomdeDataSource(datasource: HomeDataImpl):HomeDataSource

}