package com.example.themovie.repository

import com.example.themovie.model.dto.Movie
import com.example.themovie.model.dto.MovieResponse
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.MovieApi
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class HomeDataImpl @Inject constructor(private val movieApi: MovieApi):HomeDataSource {
    override suspend fun getListMovies(
        dispatcher: CoroutineDispatcher,
        HomeResultCallback: (result: NetworkResponse<List<Movie>, ErroResponse>) -> Unit
    ) {
        withContext(dispatcher){
            val showMovies = async {
                movieApi.getNowPlaying("en-Us",1)

            }

            processData(HomeResultCallback,showMovies.await())
        }


    }

    private fun processData(homeResultCallback: (result: NetworkResponse<List<Movie>, ErroResponse>)
    -> Unit, await: NetworkResponse<MovieResponse, ErroResponse>) {
        val pair = buildResponse(await)
        if (pair.first == null){
            pair.second?.let { homeResultCallback(it) }
        }else{
            homeResultCallback(NetworkResponse.Success(pair.first!!))
        }

    }

    private fun buildResponse(await: NetworkResponse<MovieResponse, ErroResponse>):
            Pair<List<Movie>?, NetworkResponse<List<Movie>,ErroResponse>? >{

        return when(await){

            is NetworkResponse.Success ->{
                Pair(await.body.results,null)
            }
            is NetworkResponse.ApiErro -> {
                Pair(null, NetworkResponse.ApiErro(await.body, await.code))
            }
            is NetworkResponse.Unknown -> {
                Pair(null, NetworkResponse.Unknown(Throwable()))
            }
            is NetworkResponse.Erro -> {
                Pair(null, NetworkResponse.Erro(IOException()))
            }
        }

    }


}