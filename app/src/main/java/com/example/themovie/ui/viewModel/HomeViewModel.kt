package com.example.themovie.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.di.IoDispatcher
import com.example.themovie.model.dto.Movie
import com.example.themovie.model.dto.tv.Tv
import com.example.themovie.network.api.MovieApi
import com.example.themovie.network.NetworkResponse
import com.example.themovie.network.api.TvApi
import com.example.themovie.repository.HomeDataSource
import com.example.themovie.repository.TvDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val movieApi: MovieApi,
    val tvApi: TvApi,
    val homeDataSource: HomeDataSource,
    val tvDataSource: TvDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

   private var _listMovies : MutableLiveData<List<Movie>> = MutableLiveData()
    val ListMovies:LiveData<List<Movie>>? = _listMovies


    private var _listTV : MutableLiveData<List<Tv>> = MutableLiveData()
    val ListTV:LiveData<List<Tv>>? = _listTV

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
            tvDataSource.getTrendingTv(dispatcher){result ->
                when(result){
                    is NetworkResponse.Success ->{
                        _listTV.postValue(result.body)
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