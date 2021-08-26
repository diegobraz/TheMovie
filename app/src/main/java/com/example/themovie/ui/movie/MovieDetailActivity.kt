package com.example.themovie.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.themovie.App
import com.example.themovie.databinding.ActivityMovieDetailBinding
import com.example.themovie.model.dto.DetailResponse
import com.example.themovie.model.dto.movie.Movie
import com.example.themovie.ui.di.MainComponent
import com.example.themovie.ui.home.viewModel.HomeViewModel
import javax.inject.Inject


class MovieDetailActivity : AppCompatActivity() {

    lateinit var movie : Movie

    lateinit var mainComponent: MainComponent

    private var details: DetailResponse? = null

    @Inject
    lateinit var vieModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MovieDetailViewModel>{ vieModelFactory }

    private val biding by lazy { ActivityMovieDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        movie = intent.getSerializableExtra("movie") as Movie

        mainComponent = (applicationContext as App).appComponent.mainComponent().create()

        mainComponent.inject(this)

        viewModel.getDetail(movie.id)

        viewModel.movie.observe(this, Observer { detailResponse ->

            details = detailResponse
            loadInfoDetail(detailResponse)

        })

        loadMovieDetail()




    }

    private fun loadInfoDetail(detailResponse: DetailResponse?) {
        biding.genreMovieDeatil.text = detailResponse?.genres?.first()?.name
        if (detailResponse?.genres?.size!! > 1){
            biding.genre2MovieDeatil.visibility = View.VISIBLE
            biding.genre2MovieDeatil.text = detailResponse.genres[1].name
        }else{
            biding.genre2MovieDeatil.visibility = View.INVISIBLE
        }

    }

    private fun loadMovieDetail() {

        Glide.with(this).load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}").into(biding.movieImageDetail)
        biding.titleMovieDetail.text = movie.title
        biding.releaseDataMovieDetail.text = movie.release_date
        biding.descriptionMovieDeatil.text = movie.overview
        biding.movieVotes.text = "${movie.vote_average} avarege vote"
    }
}