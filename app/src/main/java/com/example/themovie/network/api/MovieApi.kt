package com.example.themovie.network.api

import com.example.themovie.model.dto.MovieResponse
import com.example.themovie.network.ErroResponse
import com.example.themovie.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
   suspend fun getNowPlaying(
        @Query("language")
        language : String? = null,
        @Query("page")
        page : Int? = null,
        @Query("region")
        region : String? = null
    ): NetworkResponse<MovieResponse, ErroResponse>

}