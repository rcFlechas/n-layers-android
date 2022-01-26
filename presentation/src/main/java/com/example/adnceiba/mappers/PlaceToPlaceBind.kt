package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceBind
import com.example.domain.agregates.Place
import com.example.domain.agregates.PlaceCar
import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.mappers.Mapper

class PlaceToPlaceBind : Mapper<Place, PlaceBind> {

    override fun map(input: Place): PlaceBind {
        return when(input) {
            is PlaceMotorCycle -> {
                PlaceMotorCycleToPlaceMotorCycleBind().map(input)
            }
            else -> {
                PlaceCarToPlaceCarBind().map(input as PlaceCar)
            }
        }
    }
}