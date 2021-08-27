package com.example.themovie.ui.trending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.themovie.App
import com.example.themovie.databinding.ActivityTrendingDetailBinding
import com.example.themovie.model.domain.trendingDetail.TVDetailResponse
import com.example.themovie.model.domain.trending.Trending
import com.example.themovie.ui.trending.adapter.TrendingProductionAdapter
import com.example.themovie.ui.di.MainComponent
import com.example.themovie.ui.trending.viewModel.DetailTrendingViewModel
import javax.inject.Inject

class TrendingDetailActivity : AppCompatActivity() {


    @Inject
    lateinit var vieModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<DetailTrendingViewModel> { vieModelFactory }

    lateinit var mainComponent: MainComponent
    lateinit var trending: Trending
    private val binding by lazy { ActivityTrendingDetailBinding.inflate(layoutInflater) }

    private val productionAdapter by lazy { TrendingProductionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        trending = intent.getSerializableExtra("trending") as Trending

        mainComponent = (applicationContext as App).appComponent.mainComponent().create()

        mainComponent.inject(this)


        binding.produtionRelycleView.adapter = productionAdapter
        binding.produtionRelycleView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        viewModel.getDetail(trending.id)

        viewModel.tvTrending.observe(this, Observer { tvDetail ->
            Log.d("diegoReturn", "${tvDetail.production_companies}")
            productionAdapter.submitList(tvDetail.production_companies)
            loadInfoDetail(tvDetail)
        })


        loadInformation()

    }

    private fun loadInfoDetail(tvDetail: TVDetailResponse?) {
        binding.genreMovieDeatil.text = tvDetail?.genres?.first()?.name
        if (tvDetail?.genres?.size!! > 1) {
            binding.genre2MovieDeatil.visibility = View.VISIBLE
            binding.genre2MovieDeatil.text = tvDetail.genres[1]?.name
        } else {
            binding.genre2MovieDeatil.visibility = View.INVISIBLE
        }

    }

    private fun loadInformation() {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${trending.backdrop_path}")
            .into(binding.tvImageDetail)
        binding.tvTitle.text = trending.original_name
        Log.d("diegoMedia", "${trending.id}")
        binding.descriptionDetail.text = trending.overview
        binding.tvVotes.text = "${trending.vote_average} averege votes"

    }
}