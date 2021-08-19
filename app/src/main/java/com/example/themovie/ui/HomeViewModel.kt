package com.example.themovie.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.model.dto.MovieResponse
import com.example.themovie.network.MovieApi
import com.example.themovie.network.NetworkResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel @Inject constructor(val movieApi: MovieApi) : ViewModel() {

    fun getMovies(){
        viewModelScope.launch {
           val response =  movieApi.getNowPlaying("pt-Br",1)
            when(response){
                is NetworkResponse.Success ->{
                    Log.d("diegoresponse","Deu certo")
                }
                is NetworkResponse.ApiErro -> {
                    Log.d("diegoresponse","Erro na Api")
                }

                is NetworkResponse.Unknown ->{
                    Log.d("diegoresponse","Erro desconhecido")
                }
            }
        }

    }

}