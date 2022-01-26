package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.entities.Vehicle
import com.example.domain.mappers.Mapper

class VehicleToVehicleEntity : Mapper<Vehicle, VehicleEntity> {

    override fun map(input: Vehicle): VehicleEntity  {

        return when (input) {

            is MotorCycle -> {
                MotorCycleToVehicleEntity().map(input)
            }
            else -> {
                CarToVehicleEntity().map(input as Car)
            }
        }
    }
}