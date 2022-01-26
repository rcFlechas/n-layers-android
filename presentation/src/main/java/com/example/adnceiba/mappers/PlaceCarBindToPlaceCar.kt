package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceCarBind
import com.example.domain.agregates.PlaceCar
import com.example.domain.mappers.Mapper

class PlaceCarBindToPlaceCar : Mapper<PlaceCarBind, PlaceCar> {

    override fun map(input: PlaceCarBind): PlaceCar = with(input) {
        PlaceCar(
            id = id,
            vehicle = CarBindToCar()
                .map(vehicle),
            timeBusy = timeBusy,
            state = state,
        )
    }
}