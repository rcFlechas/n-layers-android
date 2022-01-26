package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.database.enum.TypeVehicle
import com.example.domain.entities.MotorCycle
import com.example.domain.mappers.Mapper

class MotorCycleToVehicleEntity : Mapper<MotorCycle, VehicleEntity> {

    override fun map(input: MotorCycle): VehicleEntity = with(input) {
        VehicleEntity(id, register, cylinderCapacity, TypeVehicle.MOTORCYCLE)
    }
}