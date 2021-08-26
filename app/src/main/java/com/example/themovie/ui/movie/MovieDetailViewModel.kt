package com.example.themovie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.di.IoDispatcher
import com.example.themovie.model.dto.DetailResponse
import com.example.themovie.network.NetworkResponse
import com.example.themovie.network.api.MovieApi
import com.example.themovie.repository.movie.MovieDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    val movieDataSource: MovieDataSource,
    val movieApi: MovieApi,
    @IoDispatcher val dispatcher: CoroutineDispatcher

    ): ViewModel() {

    private val _movie: MutableLiveData<DetailResponse> = MutableLiveData()
    val movie: LiveData<DetailResponse> = _movie


    fun getDetail(id:Int){
        viewModelScope.launch {
            val response = movieApi.getDetail(id)
            when(response){
                is NetworkResponse.Success ->{
                    val details = response.body
                    _movie.value = details

                }
            }
        }
    }
}