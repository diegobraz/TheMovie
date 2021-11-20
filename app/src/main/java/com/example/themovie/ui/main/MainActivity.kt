package com.example.themovie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themovie.App
import com.example.themovie.R
import com.example.themovie.databinding.ActivityMainBinding
import com.example.themovie.ui.di.MainComponent
import com.example.themovie.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private val biding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (applicationContext as App).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        val homeFragement = HomeFragment()
        val fragement = supportFragmentManager
        fragement.beginTransaction().add(R.id.main_layout, homeFragement).commit()
    }
}