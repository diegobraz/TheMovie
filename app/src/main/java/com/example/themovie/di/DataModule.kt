package com.example.themovie.di


import com.example.themovie.repository.HomeDataImpl
import com.example.themovie.repository.HomeDataSource
import com.example.themovie.repository.TvDataImpl
import com.example.themovie.repository.TvDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideHomdeDataSource(datasource: HomeDataImpl):HomeDataSource


    @Singleton
    @Binds
    abstract fun provideTVDataSouce(datasource: TvDataImpl):TvDataSource

}