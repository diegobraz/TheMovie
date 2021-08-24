package com.example.themovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.themovie.databinding.ActivityTrendingDetailBinding
import com.example.themovie.model.dto.tv.Tv

class TrendingDetailActivity : AppCompatActivity() {

    lateinit var tv : Tv
    private val biding by lazy {  ActivityTrendingDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        tv = intent.getSerializableExtra("tv") as Tv

        loadInformation()



    }

    private fun loadInformation() {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${tv.poster_path}").into(biding.tvImageDetail)
        biding.tvDeatil.text = tv.original_name
        biding.descriptionDetail.text = tv.overview
    }
}