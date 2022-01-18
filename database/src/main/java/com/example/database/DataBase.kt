package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.ParkingDAO
import com.example.database.dao.VehicleDAO
import com.example.database.entities.ParkingEntity
import com.example.database.entities.VehicleEntity

@Database(
    entities = [
        ParkingEntity::class,
        VehicleEntity::class
    ],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun parkingDAO(): ParkingDAO
    abstract fun vehicleDAO(): VehicleDAO
}