package com.example.database.dao

import androidx.room.*
import com.example.database.entities.VehicleEntity

@Dao
interface VehicleDAO {

    @Query("SELECT * FROM vehicle")
    fun getVehiclesAll(): List<VehicleEntity>

    @Query("SELECT * FROM vehicle WHERE vehi_id = :id")
    fun getVehicleById(id: Long) : VehicleEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveVehicle(vehicleEntity: VehicleEntity)
}