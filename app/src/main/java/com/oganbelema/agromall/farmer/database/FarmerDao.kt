package com.oganbelema.agromall.farmer.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.oganbelema.agromall.farmer.model.Farmer

@Dao
interface FarmerDao {

    @Insert(onConflict = IGNORE)
    fun insert(farmers: List<Farmer>)

    @Update
    fun update(farmer: Farmer)

    @Query("SELECT * FROM farmer")
    fun getFarmers(): LiveData<List<Farmer>>

    @Query("SELECT * FROM farmer WHERE farmer_id =:farmerId")
    fun getFarmer(farmerId: String): LiveData<Farmer>
}