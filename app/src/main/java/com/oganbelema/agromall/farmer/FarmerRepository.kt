package com.oganbelema.agromall.farmer

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.oganbelema.agromall.farmer.database.FarmerDao
import com.oganbelema.agromall.farmer.model.Farmer
import com.oganbelema.agromall.farmer.model.FarmerResponse
import com.oganbelema.agromall.farmer.network.FarmerNetworkDataSource
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import kotlin.coroutines.CoroutineContext

class FarmerRepository(private val farmerDao: FarmerDao,
                       private val farmerNetworkDataSource: FarmerNetworkDataSource) {

    private val tag = FarmerRepository::class.simpleName

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    fun syncFarmerData(lifeCycleOwner: LifecycleOwner){
        farmerNetworkDataSource.farmerNetworkCallResult.observe(lifeCycleOwner, Observer {
            if (it != null){
                val response = it.response

                val error = it.error

                if (response != null){
                    if (response.isSuccessful){
                        val farmerResponse = response.body()
                        if (farmerResponse != null){
                            persistFetchedFarmers(farmerResponse)
                        }
                    }
                }

                if (error != null){
                    Log.e(tag, error.localizedMessage, error)
                }
            }
        })
    }

    private fun persistFetchedFarmers(fetchedFarmerResponse: FarmerResponse){
        scope.launch {
            farmerDao.insert(fetchedFarmerResponse.data.farmers)
        }
    }

    fun getFarmers(): LiveData<List<Farmer>> {
        return farmerDao.getFarmers()
    }

    fun getFarmer(farmerId: String): LiveData<Farmer>{
        return farmerDao.getFarmer(farmerId)
    }

    fun cancelNetworkCall(){
        farmerNetworkDataSource.cancelNetworkRequest()
    }

    fun cancelDatabaseCall(){
        coroutineContext.cancel()
    }

    fun updateFarmer(farmer: Farmer) {
        //just to use anko
        doAsync {
            farmerDao.update(farmer)
        }
    }

}