package com.example.database.mappers

import com.example.database.entities.PlaceWithVehicleEntity
import com.example.domain.agregates.PlaceCar
import com.example.domain.enum.State
import com.example.domain.mappers.Mapper
import com.example.domain.valueobjects.TimeBusy

class PlaceEntityToPlaceCar : Mapper<PlaceWithVehicleEntity, PlaceCar> {

    override fun map(input: PlaceWithVehicleEntity): PlaceCar = with(input) {
        PlaceCar(
            placeEntity.id,
            VehicleEntityToCar()
                .map(vehicleEntity),
            TimeBusy(
                placeEntity.busyDate,
                placeEntity.freeDate
            ),
            State.valueOf(placeEntity.state)
        )
    }
}