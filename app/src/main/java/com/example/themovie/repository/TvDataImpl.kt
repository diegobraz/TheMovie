package com.example.themovie.repository

import com.example.themovie.model.dto.Movie
import com.example.themovie.model.dto.MovieResponse
import com.example.themovie.model.dto.tv.Tv
import com.example.themovie.model.dto.tv.TvResponse
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.NetworkResponse
import com.example.themovie.network.api.TvApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class TvDataImpl @Inject constructor(
    private val tvApi: TvApi
):TvDataSource {
    override suspend fun getTrendingTv(
        dispatcher: CoroutineDispatcher,
        HomeTvResultCallback: (result: NetworkResponse<List<Tv>, ErroResponse>) -> Unit
    ) {
        withContext(dispatcher){
            val showTredingTv = async {
                tvApi.getTrending("en-Us",1)
            }

            processData(HomeTvResultCallback,showTredingTv.await())
        }
    }

    private fun processData(homeTvResultCallback: (result: NetworkResponse<List<Tv>, ErroResponse>) -> Unit, await: NetworkResponse<TvResponse, ErroResponse>) {

        val pair = buildResponse(await)
        if (pair.first == null){
            pair.second?.let { homeTvResultCallback(it) }
        }else{
            homeTvResultCallback(NetworkResponse.Success(pair.first!!))
        }



    }

    private fun buildResponse(await: NetworkResponse<TvResponse, ErroResponse>):
            Pair<List<Tv>?, NetworkResponse<List<Tv>,ErroResponse>? >{

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