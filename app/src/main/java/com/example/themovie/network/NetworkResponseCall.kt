package com.example.themovie.network

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.lang.Exception
import java.lang.UnsupportedOperationException

internal class NetworkResponseCall <S: Any, E: Any>(
    private val delegate : Call<S>,
    private val erroConverter : Converter<ResponseBody, E>
    ): Call<NetworkResponse<S,E>> {
    override fun clone(): Call<NetworkResponse<S, E>>  = NetworkResponseCall(delegate.clone(), erroConverter)

    override fun execute(): Response<NetworkResponse<S, E>> {
       throw UnsupportedOperationException("Response Doesn't support execute")
    }

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
         return delegate.enqueue(object : Callback<S>{
             override fun onResponse(call: Call<S>, response: Response<S>) {
                 val body = response.body()
                 val code  = response.code()
                 val erro = response.errorBody()

                 if (response.isSuccessful){
                     if (body!= null){
                     callback.onResponse(
                         this@NetworkResponseCall,
                         Response.success(NetworkResponse.Success(body))
                     )
                 }
                 else{
                     callback.onResponse(this@NetworkResponseCall,
                     Response.success(NetworkResponse.Unknown())
                     )
                 }

             }else{
                 val erroBody = when{
                     erro == null -> null
                     erro.contentLength()== 0L -> null
                     else -> try {
                         erroConverter.convert(erro)
                     }catch (e:Exception){
                         null
                     }
                 }
                 if (erroBody != null){
                     callback.onResponse(this@NetworkResponseCall,
                         Response.success(NetworkResponse.ApiErro(erroBody,code))
                     )

                 }else{
                     callback.onResponse(this@NetworkResponseCall,
                         Response.success(NetworkResponse.Unknown()))
                 }

                 }
             }

             override fun onFailure(call: Call<S>, t: Throwable) {
                 callback.onResponse(this@NetworkResponseCall,
                     Response.success(NetworkResponse.Unknown()))
             }

         })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}