package com.oganbelema.agromall.farmer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.oganbelema.agromall.R
import com.oganbelema.agromall.farmer.model.Farmer
import com.oganbelema.agromall.farmer.viewmodel.FarmerViewModel
import com.oganbelema.agromall.farmer.viewmodel.FarmerViewModelFactory
import com.oganbelema.agromall.network.IMAGE_URL
import kotlinx.android.synthetic.main.activity_edit_farmer_data.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

const val FARMER_ID: String = "farmer_id"

class EditFarmerDataActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val farmerViewModelFactory: FarmerViewModelFactory by instance()

    private lateinit var farmerViewModel: FarmerViewModel

    lateinit var farmer: Farmer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_farmer_data)

        farmerViewModel = ViewModelProviders.of(this, farmerViewModelFactory).get(FarmerViewModel::class.java)

        submitButton.setOnClickListener {
            if (::farmer.isInitialized){

                farmer.firstName = farmerFirstNameEditText.text.toString()

                farmer.surname = farmerSurNameEditText.text.toString()

                farmerViewModel.update(farmer)

            }
        }

        if (intent.hasExtra(FARMER_ID)){
            val farmerId = intent.getStringExtra(FARMER_ID)

            farmerViewModel.getFarmer(farmerId).observe(this, Observer { farmer ->

                if (farmer != null){
                    this.farmer = farmer
                    Glide.with(this).load("$IMAGE_URL${farmer.passportPhoto}")
                        .into(farmerPassportImageView)
                    farmerFirstNameEditText.setText(farmer.firstName)
                    farmerSurNameEditText.setText(farmer.surname)
                }
            })

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
