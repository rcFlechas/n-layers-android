package com.example.database.mappers

import com.example.database.entities.PlaceWithVehicleEntity
import com.example.database.enum.TypeVehicle
import com.example.domain.agregates.Place
import com.example.domain.mappers.Mapper

class PlaceEntityToPlace : Mapper<PlaceWithVehicleEntity, Place> {

    override fun map(input: PlaceWithVehicleEntity): Place = with(input) {

        when (TypeVehicle.valueOf(vehicleEntity.typeVehicle)) {

            TypeVehicle.MOTORCYCLE -> {
                PlaceEntityToPlaceMotorCycle().map(input)
            }
            else -> {
                PlaceEntityToPlaceCar().map(input)
            }
        }
    }
}
