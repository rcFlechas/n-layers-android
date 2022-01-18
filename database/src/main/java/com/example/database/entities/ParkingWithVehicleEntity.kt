package com.example.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ParkingWithVehicleEntity(

    @Embedded val parkingEntity: ParkingEntity,
    @Relation(
        parentColumn = "vehi_id",
        entityColumn = "vehi_id"
    )
    val vehicleEntity: VehicleEntity
)
