package com.example.database.mappers

import com.example.database.entities.PlaceWithVehicleEntity
import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.enum.State
import com.example.domain.mappers.Mapper
import com.example.domain.valueobjects.TimeBusy

class PlaceEntityToPlaceMotorCycle : Mapper<PlaceWithVehicleEntity, PlaceMotorCycle> {

    override fun map(input: PlaceWithVehicleEntity): PlaceMotorCycle = with(input) {
        PlaceMotorCycle(
            placeEntity.id,
            VehicleEntityToMotorCycle()
                .map(vehicleEntity),
            TimeBusy(
                placeEntity.busyDate,
                placeEntity.freeDate
            ),
            State.valueOf(placeEntity.state)
        )
    }
}