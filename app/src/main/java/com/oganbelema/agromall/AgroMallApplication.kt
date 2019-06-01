package com.oganbelema.agromall

import android.app.Application
import com.oganbelema.agromall.database.AgroMallDatabase
import com.oganbelema.agromall.farmer.FarmerRepository
import com.oganbelema.agromall.farmer.viewmodel.FarmerViewModelFactory
import com.oganbelema.agromall.farmer.network.FarmerNetworkDataSource
import com.oganbelema.agromall.farmer.network.FarmerService
import com.oganbelema.agromall.network.BaseRetrofit
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AgroMallApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {

        import(androidXModule(this@AgroMallApplication))

        bind() from singleton { AgroMallDatabase(instance()) }
        bind() from singleton { BaseRetrofit() }
        bind() from singleton { FarmerService(instance()) }
        bind() from singleton { FarmerNetworkDataSource(instance()) }
        bind() from singleton { FarmerRepository(instance<AgroMallDatabase>().farmerDao(), instance()) }
        bind() from provider { FarmerViewModelFactory(instance()) }
    }
}