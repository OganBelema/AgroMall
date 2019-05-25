package com.oganbelema.agromall.farmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oganbelema.agromall.R
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class FarmerListActivity : AppCompatActivity(), KodeinAware, FarmerAdapter.Listener {
    override val kodein: Kodein by closestKodein()

    private val farmerViewModelFactory: FarmerViewModelFactory by instance()

    private lateinit var farmerViewModel: FarmerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val farmerAdapter = FarmerAdapter(this)

        farmerAdapter.setListener(this)

        farmersRecyclerView.layoutManager = LinearLayoutManager(this)

        farmersRecyclerView.setHasFixedSize(true)

        farmersRecyclerView.adapter = farmerAdapter

        farmerViewModel = ViewModelProviders.of(this, farmerViewModelFactory).get(FarmerViewModel::class.java)

        farmerViewModel.getFarmers().observe(this, Observer {farmers ->

            if (farmers != null){
                farmerAdapter.setFarmers(farmers)
                loaderViews.visibility = View.GONE
                farmersRecyclerView.visibility = View.VISIBLE
            }
        })
    }

    override fun onFarmerItemClicked(farmerId: String) {
        val startEditActivity = Intent(this, EditFarmerDataActivity::class.java)
        startEditActivity.putExtra(FARMER_ID, farmerId)
        startActivity(startEditActivity)
    }
}
