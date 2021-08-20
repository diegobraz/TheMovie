package com.example.themovie.network

import java.io.IOException

sealed class NetworkResponse<out T: Any, out U : Any>{
    data class Success<T:Any>(val body:T):NetworkResponse<T,Nothing>()

    data class Erro<T:Any>(val erro:IOException):NetworkResponse<T,Nothing>()

    data class Unknown<T:Any>(val erro:Throwable?= null):NetworkResponse<T,Nothing>()

    data class ApiErro<U:Any>(val body: U, val code : Int ):NetworkResponse<Nothing,U>()



}