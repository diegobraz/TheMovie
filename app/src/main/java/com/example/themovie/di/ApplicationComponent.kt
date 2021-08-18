package com.example.themovie.di

import android.content.Context

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [
    MainModule::class,
    NetworkModule::class,
    SubComponentModule::class,
    ViewModelBuilderModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun mainComponent(): MainComponent.factory
}

@Module(subcomponents = [MainComponent::class])
object SubComponentModule{}