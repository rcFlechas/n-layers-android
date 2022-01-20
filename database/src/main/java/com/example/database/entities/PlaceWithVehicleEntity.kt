package com.example.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PlaceWithVehicleEntity(

    @Embedded val placeEntity: PlaceEntity,
    @Relation(
        parentColumn = "vehi_id",
        entityColumn = "vehi_id"
    )
    val vehicleEntity: VehicleEntity
)
