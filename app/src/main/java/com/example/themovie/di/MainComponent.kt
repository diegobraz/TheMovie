package com.example.themovie.di


import com.example.themovie.ui.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [])
interface MainComponent {

    @Subcomponent.Factory
    interface factory{
        fun create(): MainComponent
    }


    fun inject(activity: MainActivity)

}