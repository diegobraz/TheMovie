package com.example.themovie.ui

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
import com.example.themovie.R
import com.example.themovie.databinding.HomeFragementBinding
import com.example.themovie.model.dto.Movie
import com.example.themovie.ui.adapter.MovieListAdapter
import com.example.themovie.ui.viewModel.HomeViewModel
import javax.inject.Inject
import androidx.fragment.app.FragmentManager


class HomeFragement : Fragment() {

    @Inject
    lateinit var vieModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel>{ vieModelFactory }
    private val movieAdapter by lazy { MovieListAdapter(
        onClickMovie = { movie ->
            onCreateDetailMovie(movie)
        }
    ) }



    private var _binding: HomeFragementBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragementBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieList.adapter = movieAdapter
        binding.movieList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        viewModel.getMovies()
        viewModel.getTrendingTV()


        viewModel.ListMovies?.observe(viewLifecycleOwner, Observer { MovieList ->
            movieAdapter.submitList(MovieList)

        })

        viewModel.ListTV?.observe(viewLifecycleOwner, Observer { TVList ->
           Log.d("diegoTv","${TVList}")
        })

    }

    private fun onCreateDetailMovie(movie: Movie) {

        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("movie",movie)
        startActivity(intent)

    }
}