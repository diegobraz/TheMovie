package com.example.themovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themovie.R
import com.example.themovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val biding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

    }
}