package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.domain.entities.MotorCycle
import com.example.domain.mappers.Mapper

class VehicleEntityToMotorCycle : Mapper<VehicleEntity, MotorCycle> {

    override fun map(input: VehicleEntity): MotorCycle = with(input) {

        MotorCycle(id, register, cylinderCapacity ?: 0)
    }
}
