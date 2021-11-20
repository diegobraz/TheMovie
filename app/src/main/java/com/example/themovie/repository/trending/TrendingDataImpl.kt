package com.example.themovie.repository.trending

import com.example.themovie.model.domain.trending.Trending
import com.example.themovie.model.domain.trending.TrendingResponse
import com.example.themovie.network.ErrorResponse
import com.example.themovie.network.NetworkResponse
import com.example.themovie.network.api.TrendingApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class TrendingDataImpl @Inject constructor(
    private val trendingApi: TrendingApi
) : TrendingDataSource {
    override suspend fun getTrendingTv(
        dispatcher: CoroutineDispatcher,
        HomeTvResultCallback: (result: NetworkResponse<List<Trending>, ErrorResponse>) -> Unit
    ) {
        withContext(dispatcher) {
            val showTredingTv = async {
                trendingApi.getTrending("en-Us", 1)
            }

            processData(HomeTvResultCallback, showTredingTv.await())
        }
    }

    private fun processData(
        homeTvResultCallback: (result: NetworkResponse<List<Trending>, ErrorResponse>) -> Unit,
        await: NetworkResponse<TrendingResponse, ErrorResponse>
    ) {

        val pair = buildResponse(await)
        if (pair.first == null) {
            pair.second?.let { homeTvResultCallback(it) }
        } else {
            homeTvResultCallback(NetworkResponse.Success(pair.first!!))
        }


    }

    private fun buildResponse(await: NetworkResponse<TrendingResponse, ErrorResponse>):
            Pair<List<Trending>?, NetworkResponse<List<Trending>, ErrorResponse>?> {

        return when (await) {

            is NetworkResponse.Success -> {
                Pair(await.body.results, null)
            }
            is NetworkResponse.ApiErro -> {
                Pair(null, NetworkResponse.ApiErro(await.body, await.code))
            }
            is NetworkResponse.Unknown -> {
                Pair(null, NetworkResponse.Unknown(Throwable()))
            }
            is NetworkResponse.Error -> {
                Pair(null, NetworkResponse.Error(IOException()))
            }
        }

    }


}