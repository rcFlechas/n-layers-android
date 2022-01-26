package com.example.adnceiba.mappers

import com.example.adnceiba.binds.CarBind
import com.example.adnceiba.binds.MotorCycleBind
import com.example.adnceiba.binds.VehicleBind
import com.example.domain.entities.Vehicle
import com.example.domain.mappers.Mapper

class VehicleBindToVehicle : Mapper<VehicleBind, Vehicle> {

    override fun map(input: VehicleBind): Vehicle  {

        return when(input) {
            is MotorCycleBind -> {
                MotorCycleBindToMotorCycle().map(input)
            }
            else -> {
                CarBindToCar().map(input as CarBind)
            }
        }
    }
}