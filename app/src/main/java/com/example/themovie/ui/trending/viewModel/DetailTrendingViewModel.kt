package com.example.themovie.ui.trending.viewModel

import androidx.lifecycle.*
import com.example.themovie.model.domain.trendingDetail.TVDetailResponse
import com.example.themovie.network.NetworkResponse
import com.example.themovie.network.api.TrendingApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTrendingViewModel @Inject constructor(
    private val trendingApi: TrendingApi
) : ViewModel() {

    private val _trending: MutableLiveData<TVDetailResponse> = MutableLiveData()
    val tvTrending: LiveData<TVDetailResponse> = _trending

    fun getDetail(id: Int) {
        viewModelScope.launch {
            val response = trendingApi.getTrendingDetails(id)
            when (response) {
                is NetworkResponse.Success -> {
                    val details = response.body
                    _trending.value = details

                }
            }
        }
    }
}