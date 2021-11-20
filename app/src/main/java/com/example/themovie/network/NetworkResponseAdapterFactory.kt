package com.example.themovie.network


import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory: CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        //Call<NetworkResponse<MovieResponse,ErrorResponse>>

        if (Call::class.java != getRawType(returnType)){
            return null
        }
        check(returnType is ParameterizedType){
            ""
        }

        val responseType = getParameterUpperBound(0,returnType)

        if (getRawType(responseType)!= NetworkResponse::class.java){
            return null
        }

        check(responseType is ParameterizedType){
            ""
        }

        val successBodyType = getParameterUpperBound(0,responseType)
        val erroBodytype = getParameterUpperBound(1,responseType)

        val erroBodyConverter = retrofit.nextResponseBodyConverter<Any>(null, erroBodytype, annotations)

        return NetworkResponseAdapter<Any,Any>( successBodyType,erroBodyConverter)

    }

}