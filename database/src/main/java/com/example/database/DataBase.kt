package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.converters.DateConverter
import com.example.database.dao.PlaceDAO
import com.example.database.dao.VehicleDAO
import com.example.database.entities.PlaceEntity
import com.example.database.entities.VehicleEntity

@Database(
    entities = [
        PlaceEntity::class,
        VehicleEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class
)
abstract class DataBase : RoomDatabase() {

    abstract fun placeDAO(): PlaceDAO
    abstract fun vehicleDAO(): VehicleDAO
}