package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceMotorCycleBind
import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.mappers.Mapper

class PlaceMotorCycleToPlaceMotorCycleBind : Mapper<PlaceMotorCycle, PlaceMotorCycleBind> {

    override fun map(input: PlaceMotorCycle): PlaceMotorCycleBind  {
        val placeMotorCycleBind = PlaceMotorCycleBind(
            id = input.id,
            vehicle = MotorCycleToMotorCycleBind()
                .map(input.vehicle),
            timeBusy = input.timeBusy,
            state = input.state,
        )
        placeMotorCycleBind.totalPay = input.totalPay
        return placeMotorCycleBind
    }
}