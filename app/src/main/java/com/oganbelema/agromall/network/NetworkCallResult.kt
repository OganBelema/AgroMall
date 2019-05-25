package com.oganbelema.agromall.network

import retrofit2.Response

data class NetworkCallResult<T> (val response:Response<T>? = null, val error: Throwable? = null)