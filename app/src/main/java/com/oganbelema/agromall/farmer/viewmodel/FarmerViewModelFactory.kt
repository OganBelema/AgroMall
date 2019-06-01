package com.oganbelema.agromall.farmer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oganbelema.agromall.farmer.FarmerRepository

class FarmerViewModelFactory(private val farmerRepository: FarmerRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FarmerViewModel(farmerRepository) as T
    }
}