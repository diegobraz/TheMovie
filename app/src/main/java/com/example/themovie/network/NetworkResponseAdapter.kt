package com.example.themovie.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Response
import java.lang.reflect.Type

class NetworkResponseAdapter<S:Any, E:Any>(
    private val successType: Type,
    private val erroBodyConverter: Converter<ResponseBody, E>
    ): CallAdapter< S, Call<NetworkResponse<S,E>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(call, erroBodyConverter)
    }


}