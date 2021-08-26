package com.example.themovie.repository.movie


import com.example.themovie.model.dto.movie.Movie
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

interface MovieDataSource {

    suspend fun getListMovies(dispatcher: CoroutineDispatcher,HomeResultCallback:
        (result:NetworkResponse<List<Movie>,ErroResponse> )-> Unit)

//    suspend fun getDetail(dispatcher: CoroutineDispatcher,
//    DetailResultCallback:(result:NetworkResponse<DetailMovieResponse,ErroResponse>)-> Unit, id:Int)
}