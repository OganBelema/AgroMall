package com.oganbelema.agromall.farmer.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oganbelema.agromall.farmer.FarmerResponse
import com.oganbelema.agromall.network.NetworkCallResult
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class FarmerNetworkDataSource(private val farmerService: FarmerService) {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val _farmerNetworkCallResult = MutableLiveData<NetworkCallResult<FarmerResponse>>()

    public val farmerNetworkCallResult: LiveData<NetworkCallResult<FarmerResponse>>
        get() = _farmerNetworkCallResult

    init {
        scope.launch {
            getFarmers(20, 1)
        }
    }

    private suspend fun getFarmers(limit: Int, page: Int){
        try {
            val farmerResponse = farmerService.getFarmers(limit, page).await()
            _farmerNetworkCallResult.postValue(NetworkCallResult(farmerResponse))
        } catch (error: Exception){
            _farmerNetworkCallResult.postValue(NetworkCallResult(error = error))
        }

    }

    fun cancelNetworkRequest() = coroutineContext.cancel()
}