package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.database.enum.TypeVehicle
import com.example.domain.entities.Car
import com.example.domain.mappers.Mapper

class CarToVehicleEntity : Mapper<Car, VehicleEntity> {

    override fun map(input: Car): VehicleEntity = with(input) {
        VehicleEntity(id, register, null, TypeVehicle.CAR.name)
    }
}