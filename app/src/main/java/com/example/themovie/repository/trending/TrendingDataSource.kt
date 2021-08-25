package com.example.themovie.repository.trending

import com.example.themovie.model.dto.Trending.Trending
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

interface TrendingDataSource {

    suspend fun getTrendingTv(dispatcher: CoroutineDispatcher, HomeTvResultCallback:
    (result: NetworkResponse<List<Trending>, ErroResponse>)-> Unit)

}