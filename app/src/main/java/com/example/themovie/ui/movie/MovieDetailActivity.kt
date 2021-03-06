package com.example.themovie.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.themovie.App
import com.example.themovie.BuildConfig
import com.example.themovie.databinding.ActivityMovieDetailBinding
import com.example.themovie.model.domain.movieDetail.DetailMovieResponse
import com.example.themovie.model.domain.movie.Movie
import com.example.themovie.ui.movie.adapter.MovieProductionAdapter
import com.example.themovie.ui.di.MainComponent
import com.example.themovie.ui.movie.viewModel.MovieDetailViewModel
import javax.inject.Inject


class MovieDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var vieModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MovieDetailViewModel> { vieModelFactory }
    private val binding by lazy { ActivityMovieDetailBinding.inflate(layoutInflater) }
    lateinit var movie: Movie
    lateinit var mainComponent: MainComponent
    private var details: DetailMovieResponse? = null
    private val productionAdapter by lazy { MovieProductionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        movie = intent.getSerializableExtra("movie") as Movie

        mainComponent = (applicationContext as App).appComponent.mainComponent().create()

        mainComponent.inject(this)

        viewModel.getDetail(movie.id)

        binding.productionRecycleView.adapter = productionAdapter
        binding.productionRecycleView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        viewModel.movie.observe(this, Observer { detailResponse ->
            details = detailResponse
            loadInfoDetail(detailResponse)
            productionAdapter.submitList(detailResponse.production_companies)
        })

        loadMovieDetail()
    }

    private fun loadInfoDetail(detailMovieResponse: DetailMovieResponse?) {
        binding.genreMovieDetail.text = detailMovieResponse?.genres?.first()?.name
        if (detailMovieResponse?.genres?.size!! > 1) {
            binding.genre2MovieDetail.visibility = View.VISIBLE
            binding.genre2MovieDetail.text = detailMovieResponse.genres[1].name
        } else {
            binding.genre2MovieDetail.visibility = View.INVISIBLE
        }
    }

    private fun loadMovieDetail() {
        Glide.with(this).load(BuildConfig.BASE_IMAGE + movie.backdrop_path)
            .into(binding.movieImageDetail)
        binding.titleMovieDetail.text = movie.title
        binding.releaseDataMovieDetail.text = movie.release_date
        binding.descriptionMovieDeatil.text = movie.overview
        binding.movieVotes.text = "${movie.vote_average} avarege vote"
    }
}