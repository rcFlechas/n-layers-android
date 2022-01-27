package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceCarBind
import com.example.domain.agregates.PlaceCar
import com.example.domain.mappers.Mapper

class PlaceCarToPlaceCarBind : Mapper<PlaceCar, PlaceCarBind> {

    override fun map(input: PlaceCar): PlaceCarBind {
        val placeCarBind = PlaceCarBind(
            id = input.id,
            vehicle = CarToCarBind()
                .map(input.vehicle),
            timeBusy = input.timeBusy,
            state = input.state,
        )
        placeCarBind.totalPay = input.totalPay
        return placeCarBind
    }
}