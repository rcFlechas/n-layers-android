package com.example.database.dao

import androidx.room.*
import com.example.database.entities.VehicleEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface VehicleDAO {

    @Transaction
    @Query("SELECT * FROM vehicleEntity")
    fun getVehiclesAll(): Single<List<VehicleEntity>>

    @Query("SELECT * FROM vehicleEntity WHERE vehi_id = :id")
    fun getVehicleById(id: Long) : Maybe<VehicleEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveVehicle(vehicleEntity: VehicleEntity): Completable
}