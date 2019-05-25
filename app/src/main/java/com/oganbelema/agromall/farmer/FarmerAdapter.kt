package com.oganbelema.agromall.farmer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oganbelema.agromall.R
import com.oganbelema.agromall.network.IMAGE_URL
import de.hdodenhof.circleimageview.CircleImageView

class FarmerAdapter(private val context: Context): RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder>() {

    interface Listener {
        fun onFarmerItemClicked(farmerId: String)
    }

    private val farmers = ArrayList<Farmer>(0)

    private var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerViewHolder {
        return FarmerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.farmer_item, parent,
            false))
    }

    override fun getItemCount(): Int = farmers.size

    fun setFarmers(farmers: List<Farmer>){
        this.farmers.clear()
        this.farmers.addAll(farmers)
        notifyDataSetChanged()
    }

    fun setListener(listener: Listener){
        this.listener = listener
    }

    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        holder.bindData(farmers[position])

    }

    inner class FarmerViewHolder(private val farmerItemView: View): RecyclerView.ViewHolder(farmerItemView) {

        private val farmerPassportImageView = farmerItemView.findViewById<CircleImageView>(R.id.farmerPassportImageView)

        private val farmerNameTextView = farmerItemView.findViewById<TextView>(R.id.farmerNameTextView)


        fun bindData(farmer: Farmer){
            Glide.with(context).load("$IMAGE_URL${farmer.passportPhoto}")
                .error(R.drawable.ic_error_24dp)
                .into(farmerPassportImageView)

            farmerNameTextView.text = "${farmer.firstName} ${farmer.surname}"

            farmerItemView.setOnClickListener {
                if (listener != null){
                    listener?.onFarmerItemClicked(farmer.farmerId)
                }
            }

        }

    }
}