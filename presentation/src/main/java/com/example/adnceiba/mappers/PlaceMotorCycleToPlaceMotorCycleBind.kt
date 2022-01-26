package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceMotorCycleBind
import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.mappers.Mapper

class PlaceMotorCycleToPlaceMotorCycleBind : Mapper<PlaceMotorCycle, PlaceMotorCycleBind> {

    override fun map(input: PlaceMotorCycle): PlaceMotorCycleBind = with(input) {
        PlaceMotorCycleBind(
            id = id,
            vehicle = MotorCycleToMotorCycleBind()
                .map(vehicle),
            timeBusy = timeBusy,
            state = state,
            totalPay = totalPay
        )
    }
}