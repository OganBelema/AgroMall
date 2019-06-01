package com.oganbelema.agromall.farmer.service

import androidx.lifecycle.LifecycleService
import com.oganbelema.agromall.farmer.FarmerRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SyncFarmerDataService: LifecycleService(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val repository: FarmerRepository by instance()

    override fun onCreate() {
        super.onCreate()
        repository.syncFarmerData(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.cancelNetworkCall()
        repository.cancelDatabaseCall()
    }

}