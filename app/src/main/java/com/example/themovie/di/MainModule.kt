package com.example.themovie.di

import androidx.lifecycle.ViewModel
import com.example.themovie.ui.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindMainViewModel(homeViewModel: HomeViewModel):ViewModel
}