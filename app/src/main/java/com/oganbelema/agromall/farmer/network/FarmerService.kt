package com.oganbelema.agromall.farmer.network

import com.oganbelema.agromall.farmer.model.FarmerResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FarmerService {

    companion object {
        operator fun invoke(retrofit: Retrofit): FarmerService {
            return retrofit.create(FarmerService::class.java)
        }
    }

    @FormUrlEncoded
    @POST("get-sample-farmers")
    fun getFarmers(@Field("limit") limit: Int, @Field("page") page: Int): Deferred<Response<FarmerResponse>>
}