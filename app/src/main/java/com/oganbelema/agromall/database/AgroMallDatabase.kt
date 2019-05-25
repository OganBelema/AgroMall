package com.oganbelema.agromall.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oganbelema.agromall.farmer.Farmer
import com.oganbelema.agromall.farmer.database.FarmerDao

@Database(entities = [Farmer::class], version = 1, exportSchema = false)
abstract class AgroMallDatabase : RoomDatabase(){
    abstract fun farmerDao(): FarmerDao

    companion object {
        @Volatile private var instance: AgroMallDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AgroMallDatabase::class.java,
                "AgroMallDatabase.db").build()
    }
}