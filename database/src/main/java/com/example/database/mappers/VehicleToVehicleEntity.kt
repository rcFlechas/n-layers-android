package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.domain.entities.MotorCycle
import com.example.domain.entities.Vehicle
import com.example.domain.mappers.Mapper

class VehicleToVehicleEntity : Mapper<Vehicle, VehicleEntity> {

    override fun map(input: Vehicle): VehicleEntity = with(input) {

        when (this) {

            is MotorCycle -> {
                val motorCycle: MotorCycle = this

                VehicleEntity(
                    id = motorCycle.id,
                    register = motorCycle.register,
                    cylinderCapacity = motorCycle.cylinderCapacity
                )
            }
            else -> {
                VehicleEntity(
                    id = id,
                    register = register
                )
            }
        }
    }
}