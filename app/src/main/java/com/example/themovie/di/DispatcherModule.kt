package com.example.themovie.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
object DispatcherModule {

    @DefaultDispatcher
    @Provides
    fun providerDefautDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providerIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providerMainDispatcher(): CoroutineDispatcher = Dispatchers.Main


}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher