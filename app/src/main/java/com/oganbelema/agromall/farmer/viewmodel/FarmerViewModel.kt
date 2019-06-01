package com.oganbelema.agromall.farmer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oganbelema.agromall.farmer.model.Farmer
import com.oganbelema.agromall.farmer.FarmerRepository

class FarmerViewModel(private val farmerRepository: FarmerRepository): ViewModel() {

    fun getFarmers(): LiveData<List<Farmer>> = farmerRepository.getFarmers()

    fun getFarmer(farmerId: String): LiveData<Farmer> = farmerRepository.getFarmer(farmerId)

    fun update(farmer: Farmer) = farmerRepository.updateFarmer(farmer)

    override fun onCleared() {
        farmerRepository.cancelNetworkCall()
        farmerRepository.cancelDatabaseCall()
        super.onCleared()
    }

}