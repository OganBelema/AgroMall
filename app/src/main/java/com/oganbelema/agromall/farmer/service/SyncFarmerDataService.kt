package com.oganbelema.agromall.farmer.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.oganbelema.agromall.farmer.FarmerRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SyncFarmerDataService: LifecycleService(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val repository: FarmerRepository by instance()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        repository.syncFarmerData(this)
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.cancelNetworkCall()
        repository.cancelDatabaseCall()
    }

}