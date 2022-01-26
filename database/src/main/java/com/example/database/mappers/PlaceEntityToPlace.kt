package com.example.database.mappers

import com.example.database.entities.PlaceWithVehicleEntity
import com.example.database.enum.TypeVehicle
import com.example.domain.agregates.Place
import com.example.domain.agregates.PlaceCar
import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.enum.State
import com.example.domain.mappers.Mapper
import com.example.domain.valueobjects.TimeBusy

class PlaceEntityToPlace : Mapper<PlaceWithVehicleEntity, Place> {

    override fun map(input: PlaceWithVehicleEntity): Place = with(input) {

        when (TypeVehicle.valueOf(vehicleEntity.typeVehicle)) {

            TypeVehicle.MOTORCYCLE -> {
                PlaceMotorCycle(
                    id = placeEntity.id,
                    vehicle = MotorCycle(
                        id = vehicleEntity.id,
                        register = vehicleEntity.register,
                        cylinderCapacity = vehicleEntity.cylinderCapacity ?: 0
                    ),
                    timeBusy = TimeBusy(
                        busyDate = placeEntity.busyDate,
                        freeDate = placeEntity.freeDate
                    ),
                    state = State.valueOf(placeEntity.state)
                )
            }
            else -> {
                PlaceCar(
                    id = placeEntity.id,
                    vehicle = Car(
                        id = vehicleEntity.id,
                        register = vehicleEntity.register
                    ),
                    timeBusy = TimeBusy(
                        busyDate = placeEntity.busyDate,
                        freeDate = placeEntity.freeDate
                    ),
                    state = State.valueOf(placeEntity.state)
                )
            }
        }
    }
}
