package com.example.themovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themovie.App
import com.example.themovie.databinding.ActivityMainBinding
import com.example.themovie.di.MainComponent

class MainActivity : AppCompatActivity() {

    private val biding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (applicationContext as App).appComponent.mainComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(biding.root)

    }
}