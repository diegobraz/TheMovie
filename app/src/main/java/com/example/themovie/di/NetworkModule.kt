package com.example.themovie.di

import com.example.themovie.utils.AppConstants
import com.example.themovie.network.api.MovieApi
import com.example.themovie.network.NetworkResponseAdapterFactory
import com.example.themovie.network.api.TrendingApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesInteceptor(): Interceptor{
        return Interceptor { chain ->
            val newUrl:HttpUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", AppConstants.TMDB_API_KEY)
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Singleton
    @Provides
    fun LoggingClient(authInterceptor: Interceptor):OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(30,TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addNetworkInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitInteance(logginClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(logginClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun tmdApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }


    @Singleton
    @Provides
    fun tmdTvApi(retrofit: Retrofit):TrendingApi{
        return retrofit.create(TrendingApi::class.java)
    }



}