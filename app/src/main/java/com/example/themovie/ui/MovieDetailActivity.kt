package com.example.themovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.themovie.databinding.ActivityMovieDetailBinding
import com.example.themovie.model.dto.movie.Movie


class MovieDetailActivity : AppCompatActivity() {

    lateinit var movie : Movie

    private val biding by lazy { ActivityMovieDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        movie = intent.getSerializableExtra("movie") as Movie

        loadMovieDetail()
    }

    private fun loadMovieDetail() {

        Glide.with(this).load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(biding.movieImageDetail)
        biding.titleMovieDeatil.text = movie.title
        biding.descriptionMovieDeatil.text = movie.overview
        biding.movieVotes.text = "${movie.vote_average} avarege vote"
    }
}