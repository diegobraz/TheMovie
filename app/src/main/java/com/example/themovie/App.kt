package com.example.themovie

import android.app.Application
import com.example.themovie.di.DaggerApplicationComponent


class App : Application() {

    val appComponent by lazy { DaggerApplicationComponent.factory().create(this) }

}