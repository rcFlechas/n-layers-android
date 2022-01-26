package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.database.enum.TypeVehicle
import com.example.domain.entities.Vehicle
import com.example.domain.mappers.Mapper

class VehicleEntityToVehicle : Mapper<VehicleEntity, Vehicle> {

    override fun map(input: VehicleEntity): Vehicle  {

        return when(TypeVehicle.valueOf(input.typeVehicle)) {
            TypeVehicle.MOTORCYCLE -> {
                VehicleEntityToMotorCycle().map(input)
            }
            else -> {
                VehicleEntityToCar().map(input)
            }
        }
    }
}

