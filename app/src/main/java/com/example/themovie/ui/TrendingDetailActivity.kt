package com.example.themovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.themovie.databinding.ActivityTrendingDetailBinding
import com.example.themovie.model.dto.Trending.Trending

class TrendingDetailActivity : AppCompatActivity() {

    lateinit var trending : Trending
    private val biding by lazy {  ActivityTrendingDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        trending = intent.getSerializableExtra("trending") as Trending

        loadInformation()



    }

    private fun loadInformation() {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${trending.poster_path}").into(biding.tvImageDetail)
        biding.tvTitle.text = trending.original_name
        biding.descriptionDetail.text = trending.overview
        biding.tvVotes.text = "${trending.vote_average} averege votes"
    }
}