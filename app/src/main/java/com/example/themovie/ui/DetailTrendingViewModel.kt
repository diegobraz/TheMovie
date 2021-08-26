package com.example.themovie.ui

import androidx.activity.viewModels
import androidx.lifecycle.*
import com.example.themovie.model.dto.TVDetailResponse
import com.example.themovie.model.dto.movieDetail.DetailMovieResponse
import com.example.themovie.network.NetworkResponse
import com.example.themovie.network.api.TrendingApi
import com.example.themovie.repository.trending.TrendingDataSource
import com.example.themovie.ui.movie.MovieDetailViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTrendingViewModel @Inject constructor(
    val trendingDataSource: TrendingDataSource,
    val trendingApi: TrendingApi
): ViewModel() {


    private val _trending: MutableLiveData<TVDetailResponse> = MutableLiveData()
    val tvTrending: LiveData<TVDetailResponse> = _trending


    fun getDetail(id:Int){
        viewModelScope.launch {
            val response = trendingApi.getTrendingDetails(id)
            when(response){
                is NetworkResponse.Success ->{
                    val details = response.body
                    _trending.value = details

                }
            }
        }
    }


}