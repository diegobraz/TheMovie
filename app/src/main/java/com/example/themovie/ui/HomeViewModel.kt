package com.example.themovie.ui

import androidx.lifecycle.ViewModel
import com.example.themovie.model.dto.MovieResponse
import com.example.themovie.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel @Inject constructor(val movieApi: MovieApi) : ViewModel() {

    fun getMovies(){
        movieApi.getNowPlaying("pt-Br",1).enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }

}