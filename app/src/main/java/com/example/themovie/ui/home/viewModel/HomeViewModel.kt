package com.example.themovie.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.di.IoDispatcher
import com.example.themovie.model.domain.movie.Movie
import com.example.themovie.model.domain.trending.Trending
import com.example.themovie.network.NetworkResponse
import com.example.themovie.repository.movie.MovieDataSource
import com.example.themovie.repository.trending.TrendingDataSource
import com.example.themovie.utils.GenericErro
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val trendingDataSource: TrendingDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _listMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val listMovies: LiveData<List<Movie>>? = _listMovies

    private var _errorMessage: String? = null
    val errorMessage: String? = _errorMessage

    private var _listTrending: MutableLiveData<List<Trending>> = MutableLiveData()
    val listTrending: LiveData<List<Trending>>? = _listTrending

    fun getMovies() {
        viewModelScope.launch(dispatcher) {
            movieDataSource.getListMovies(dispatcher) { result ->
                when (result) {
                    is NetworkResponse.Success -> {
                        _listMovies.postValue(result.body)
                    }
                    is NetworkResponse.Unknown -> {
                        _errorMessage = GenericErro.ERROR_UNKNOWN
                    }
                    is NetworkResponse.ApiErro -> {
                        _errorMessage = GenericErro.ERROR_API
                    }
                    is NetworkResponse.Error -> {
                        _errorMessage = GenericErro.ERROR
                    }
                }
            }
        }
    }

    fun getTrendingTV() {
        viewModelScope.launch(dispatcher) {
            trendingDataSource.getTrendingTv(dispatcher) { result ->
                when (result) {
                    is NetworkResponse.Success -> {
                        _listTrending.postValue(result.body)
                    }
                    is NetworkResponse.Unknown -> {
                        _errorMessage = GenericErro.ERROR_UNKNOWN
                    }
                    is NetworkResponse.ApiErro -> {
                        _errorMessage = GenericErro.ERROR_API
                    }
                    is NetworkResponse.Error -> {
                        _errorMessage = GenericErro.ERROR
                    }
                }
            }
        }
    }
}