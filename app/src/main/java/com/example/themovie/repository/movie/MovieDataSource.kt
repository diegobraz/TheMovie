package com.example.themovie.repository.movie


import com.example.themovie.model.domain.movie.Movie
import com.example.themovie.network.ErrorResponse
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

interface MovieDataSource {

    suspend fun getListMovies(
        dispatcher: CoroutineDispatcher, HomeResultCallback:
            (result: NetworkResponse<List<Movie>, ErrorResponse>) -> Unit
    )

}