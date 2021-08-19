package com.example.themovie.network



import com.example.themovie.model.dto.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Error

interface MovieApi {

    @GET("movie/now_playing")
   suspend fun getNowPlaying(
        @Query("language")
        language : String? = null,
        @Query("page")
        page : Int? = null,
        @Query("region")
        region : String? = null
    ): NetworkResponse<MovieResponse,ErroResponse>

}