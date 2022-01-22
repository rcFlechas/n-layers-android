package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.domain.entities.Vehicle
import com.example.domain.mappers.Mapper

class VehicleEntityToVehicle : Mapper<VehicleEntity, Vehicle> {

    override fun map(input: VehicleEntity): Vehicle = with(input) {
        Vehicle(id, register)
    }
}

