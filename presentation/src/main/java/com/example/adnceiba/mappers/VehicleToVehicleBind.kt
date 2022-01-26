package com.example.adnceiba.mappers

import com.example.adnceiba.binds.VehicleBind
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.entities.Vehicle
import com.example.domain.mappers.Mapper

class VehicleToVehicleBind : Mapper<Vehicle, VehicleBind> {

    override fun map(input: Vehicle): VehicleBind {

        return when(input) {
            is MotorCycle -> {
                MotorCycleToMotorCycleBind().map(input)
            }
            else -> {
                CarToCarBind().map(input as Car)
            }
        }
    }
}