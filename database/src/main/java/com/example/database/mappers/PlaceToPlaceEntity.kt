package com.example.database.mappers

import com.example.database.entities.PlaceEntity
import com.example.domain.agregates.Place
import com.example.domain.mappers.Mapper

class PlaceToPlaceEntity : Mapper<Place, PlaceEntity> {

    override fun map(input: Place): PlaceEntity = with(input) {
        PlaceEntity(
            id = id,
            busyDate = timeBusy.busyDate,
            freeDate = timeBusy.freeDate,
            state = state,
            vehicleId = vehicle.id
        )
    }
}