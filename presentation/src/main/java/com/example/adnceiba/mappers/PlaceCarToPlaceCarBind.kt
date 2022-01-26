package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceCarBind
import com.example.domain.agregates.PlaceCar
import com.example.domain.mappers.Mapper

class PlaceCarToPlaceCarBind : Mapper<PlaceCar, PlaceCarBind> {

    override fun map(input: PlaceCar): PlaceCarBind = with(input) {
        PlaceCarBind(
            id = id,
            vehicle = CarToCarBind()
                .map(vehicle),
            timeBusy = timeBusy,
            state = state,
            totalPay = totalPay
        )
    }
}