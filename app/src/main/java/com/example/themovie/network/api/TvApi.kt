package com.example.themovie.network.api

import com.example.themovie.model.dto.tv.TvResponse
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApi {
    @GET("trending/movie/day")
    suspend fun getTrending(
        @Query("language")
        language : String? = null,
        @Query("page")
        page : Int? = null,
        @Query("region")
        region : String? = null

    ): NetworkResponse<TvResponse, ErroResponse>

}