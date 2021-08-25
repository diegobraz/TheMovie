package com.example.themovie.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.di.IoDispatcher
import com.example.themovie.model.dto.movie.Movie
import com.example.themovie.model.dto.Trending.Trending
import com.example.themovie.network.api.MovieApi
import com.example.themovie.network.NetworkResponse
import com.example.themovie.network.api.TrendingApi
import com.example.themovie.repository.movie.MovieDataSource
import com.example.themovie.repository.trending.TrendingDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val movieApi: MovieApi,
    val trendingApi: TrendingApi,
    val homeDataSource: MovieDataSource,
    val trendingDataSource: TrendingDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

   private var _listMovies : MutableLiveData<List<Movie>> = MutableLiveData()
    val ListMovies:LiveData<List<Movie>>? = _listMovies


    private var _listTrending : MutableLiveData<List<Trending>> = MutableLiveData()
    val listTrending:LiveData<List<Trending>>? = _listTrending

    fun getMovies(){
        viewModelScope.launch(dispatcher) {
            homeDataSource.getListMovies(dispatcher){result ->

                when(result){
                    is NetworkResponse.Success ->{
                        _listMovies.postValue(result.body)
                    }

                    is NetworkResponse.Unknown -> {}
                    is NetworkResponse.ApiErro -> {}
                    is NetworkResponse.Erro -> {}
                }

            }
        }

    }


    fun getTrendingTV(){
        viewModelScope.launch(dispatcher){
            trendingDataSource.getTrendingTv(dispatcher){ result ->
                when(result){
                    is NetworkResponse.Success ->{
                        _listTrending.postValue(result.body)
                    }

                    is NetworkResponse.Unknown -> {
                        Log.d("diegoTV","erro")
                    }
                    is NetworkResponse.ApiErro -> {
                        Log.d("diegoTV","erro")
                    }
                    is NetworkResponse.Erro -> {
                        Log.d("diegoTV","erro")
                    }
                }

            }
        }
    }

}