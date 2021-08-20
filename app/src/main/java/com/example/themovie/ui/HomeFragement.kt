package com.example.themovie.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovie.R
import com.example.themovie.databinding.HomeFragementBinding
import com.example.themovie.ui.adapter.MovieListAdapter
import com.example.themovie.ui.viewModel.HomeViewModel
import javax.inject.Inject

class HomeFragement : Fragment() {

    @Inject
    lateinit var vieModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel>{ vieModelFactory }
    private val movieAdapter by lazy { MovieListAdapter() }
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
        binding.movieList.layoutManager =LinearLayoutManager(requireContext())

        viewModel.getMovies()
        viewModel.ListMovies?.observe(viewLifecycleOwner, Observer { MovieList ->
            movieAdapter.submitList(MovieList)

        })

    }
}