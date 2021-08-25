package com.example.themovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themovie.App
import com.example.themovie.R
import com.example.themovie.databinding.ActivityMainBinding
import com.example.themovie.ui.di.MainComponent

class MainActivity : AppCompatActivity() {

    private val biding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        mainComponent = (applicationContext as App).appComponent.mainComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        val homeFragement = HomeFragement()
        val fragement = supportFragmentManager
        fragement.beginTransaction().add(R.id.main_layout,homeFragement).commit()

    }
}