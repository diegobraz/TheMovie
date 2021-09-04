package com.example.themovie.ui.di


import com.example.themovie.ui.home.HomeFragment
import com.example.themovie.ui.main.MainActivity
import com.example.themovie.ui.movie.MovieDetailActivity
import com.example.themovie.ui.trending.TrendingDetailActivity
import dagger.Subcomponent

@Subcomponent(modules = [])
interface MainComponent {

    @Subcomponent.Factory
    interface factory {
        fun create(): MainComponent
    }


    fun inject(activity: MainActivity)
    fun inject(activity: MovieDetailActivity)
    fun inject(fragment: HomeFragment)
    fun inject(activity: TrendingDetailActivity)
}