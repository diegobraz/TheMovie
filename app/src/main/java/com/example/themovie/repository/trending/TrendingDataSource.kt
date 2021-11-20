package com.example.themovie.repository.trending

import com.example.themovie.model.domain.trending.Trending
import com.example.themovie.network.ErrorResponse
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

interface TrendingDataSource {

    suspend fun getTrendingTv(
        dispatcher: CoroutineDispatcher, HomeTvResultCallback:
            (result: NetworkResponse<List<Trending>, ErrorResponse>) -> Unit
    )

}