package com.nwar.dsm.deanomoo_dsm.Connect

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val URL = "http://aws.jaehoon.kim:5002/"
    private val httpBuilder = OkHttpClient.Builder()
    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpBuilder.build())
    val retrofit : Retrofit = builder.build()

    fun <S> createService(serviceClass: Class<S>) : S{
        return builder.build().create(serviceClass)
    }
}