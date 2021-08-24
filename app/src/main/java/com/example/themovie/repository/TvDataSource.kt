package com.example.themovie.repository

import com.example.themovie.model.dto.tv.Tv
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

interface TvDataSource {

    suspend fun getTrendingTv(dispatcher: CoroutineDispatcher, HomeTvResultCallback:
    (result: NetworkResponse<List<Tv>, ErroResponse>)-> Unit)

}