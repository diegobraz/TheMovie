package com.example.themovie.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.themovie.R
import javax.inject.Inject

class HomeFragement : Fragment() {

    @Inject
    lateinit var vieModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel>{ vieModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragement, container, false)

        viewModel.getMovies()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        viewModel.ListMovies?.observe(viewLifecycleOwner, Observer { MovieList ->

        })

    }
}