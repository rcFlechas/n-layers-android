package com.example.database.mappers

import com.example.database.entities.VehicleEntity
import com.example.domain.entities.Car
import com.example.domain.mappers.Mapper

class VehicleEntityToCar : Mapper<VehicleEntity, Car> {

    override fun map(input: VehicleEntity): Car = with(input) {
        Car(id, register)
    }
}