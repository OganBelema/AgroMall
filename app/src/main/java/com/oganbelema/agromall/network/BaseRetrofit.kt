package com.oganbelema.agromall.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://theagromall.com/api/v2/"

const val IMAGE_URL = "https://s3-eu-west-1.amazonaws.com/agromall-storage/"

object BaseRetrofit {

    operator fun invoke(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    }
}