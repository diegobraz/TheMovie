package com.example.themovie.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.di.IoDispatcher
import com.example.themovie.model.dto.Movie
import com.example.themovie.model.dto.MovieResponse
import com.example.themovie.network.MovieApi
import com.example.themovie.network.NetworkResponse
import com.example.themovie.repository.HomeDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val movieApi: MovieApi,
    val homeDataSource: HomeDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

   private var _listMovies : MutableLiveData<List<Movie>> = MutableLiveData()
    val ListMovies:LiveData<List<Movie>>? = _listMovies



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

}