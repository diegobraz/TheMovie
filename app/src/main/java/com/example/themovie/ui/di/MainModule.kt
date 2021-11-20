package com.example.themovie.ui.di

import androidx.lifecycle.ViewModel
import com.example.themovie.di.ViewModelKey
import com.example.themovie.ui.trending.viewModel.DetailTrendingViewModel
import com.example.themovie.ui.home.viewModel.HomeViewModel
import com.example.themovie.ui.movie.viewModel.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindMainViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailTrendingViewModel::class)
    fun bindTrendingDetailViewModel(trendingViewModel: DetailTrendingViewModel): ViewModel

}