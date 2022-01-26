package com.example.adnceiba.mappers

import com.example.adnceiba.binds.MotorCycleBind
import com.example.domain.entities.MotorCycle
import com.example.domain.mappers.Mapper

class MotorCycleBindToMotorCycle : Mapper<MotorCycleBind, MotorCycle> {

    override fun map(input: MotorCycleBind): MotorCycle = with(input) {
        MotorCycle(id, register, cylinderCapacity)
    }
}
