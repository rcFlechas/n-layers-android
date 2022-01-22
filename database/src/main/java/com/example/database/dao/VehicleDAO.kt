package com.example.database.dao

import androidx.room.*
import com.example.database.entities.VehicleEntity

@Dao
interface VehicleDAO {

    @Transaction
    @Query("SELECT * FROM vehicleEntity")
    fun getVehiclesAll(): List<VehicleEntity>

    @Query("SELECT * FROM vehicleEntity WHERE vehi_id = :id")
    fun getVehicleById(id: Long) : VehicleEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveVehicle(vehicleEntity: VehicleEntity): Boolean
}