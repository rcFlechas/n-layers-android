package com.example.adnceiba.mappers

import com.example.adnceiba.binds.MotorCycleBind
import com.example.domain.entities.MotorCycle
import com.example.domain.mappers.Mapper

class MotorCycleToMotorCycleBind : Mapper<MotorCycle, MotorCycleBind> {

    override fun map(input: MotorCycle): MotorCycleBind = with(input) {
        MotorCycleBind(id, register, cylinderCapacity)
    }
}