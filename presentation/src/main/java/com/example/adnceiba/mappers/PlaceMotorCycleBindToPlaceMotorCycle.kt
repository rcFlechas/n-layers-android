package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceMotorCycleBind
import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.mappers.Mapper

class PlaceMotorCycleBindToPlaceMotorCycle : Mapper<PlaceMotorCycleBind, PlaceMotorCycle> {

    override fun map(input: PlaceMotorCycleBind): PlaceMotorCycle = with(input) {
        PlaceMotorCycle(
            id = id,
            vehicle = MotorCycleBindToMotorCycle()
                .map(vehicle),
            timeBusy = timeBusy,
            state = state
        )
    }
}