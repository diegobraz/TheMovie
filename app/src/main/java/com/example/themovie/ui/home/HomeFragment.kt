package com.example.themovie.ui.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovie.databinding.HomeFragmentBinding
import com.example.themovie.model.domain.movie.Movie
import com.example.themovie.ui.movie.adapter.MovieListAdapter
import com.example.themovie.ui.home.viewModel.HomeViewModel
import javax.inject.Inject
import com.example.themovie.model.domain.trending.Trending
import com.example.themovie.ui.main.MainActivity
import com.example.themovie.ui.movie.MovieDetailActivity
import com.example.themovie.ui.trending.TrendingDetailActivity
import com.example.themovie.ui.trending.adapter.TrendingListAdapter


class HomeFragment : Fragment() {

    @Inject
    lateinit var vieModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { vieModelFactory }

    private val movieAdapter by lazy {
        MovieListAdapter(
            onClickMovie = { movie ->
                onCreateDetailMovie(movie)
            }
        )
    }


    private val trendingAdapter by lazy {
        TrendingListAdapter(
            onClickMovie = { trending ->
                onCreateTrendingDetail(trending)
            }
        )
    }


    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieList.adapter = movieAdapter
        binding.movieList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.tvList.adapter = trendingAdapter
        binding.tvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.getMovies()
        viewModel.getTrendingTV()


        viewModel.listMovies?.observe(viewLifecycleOwner, Observer { MovieList ->
            movieAdapter.submitList(MovieList)
        })

        if (!viewModel.erroMessage.isNullOrBlank()) {
            Toast.makeText(requireContext(), viewModel.erroMessage, Toast.LENGTH_SHORT).show()
        }

        viewModel.listTrending?.observe(viewLifecycleOwner, Observer { TVList ->
            trendingAdapter.submitList(TVList)
        })

    }

    private fun onCreateDetailMovie(movie: Movie) {

        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)

    }


    private fun onCreateTrendingDetail(trending: Trending) {

        val intent = Intent(activity, TrendingDetailActivity::class.java)
        intent.putExtra("trending", trending)
        startActivity(intent)
    }

}