package com.example.themovie.repository


import com.example.themovie.model.dto.Movie
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

interface HomeDataSource {

    suspend fun getListMovies(dispatcher: CoroutineDispatcher,HomeResultCallback:
        (result:NetworkResponse<List<Movie>,ErroResponse> )-> Unit)

}