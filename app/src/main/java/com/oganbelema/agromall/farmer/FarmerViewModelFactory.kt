package com.oganbelema.agromall.farmer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FarmerViewModelFactory(private val farmerRepository: FarmerRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FarmerViewModel(farmerRepository) as T
    }
}