package com.example.themovie.di


import com.example.themovie.repository.movie.MovieDataImpl
import com.example.themovie.repository.movie.MovieDataSource
import com.example.themovie.repository.trending.TrendingDataImpl
import com.example.themovie.repository.trending.TrendingDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideHomdeDataSource(datasource: MovieDataImpl): MovieDataSource


    @Singleton
    @Binds
    abstract fun provideTVDataSouce(datasource: TrendingDataImpl): TrendingDataSource

}