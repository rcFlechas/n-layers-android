package com.example.adnceiba.mappers

import com.example.adnceiba.binds.PlaceBind
import com.example.adnceiba.binds.PlaceCarBind
import com.example.adnceiba.binds.PlaceMotorCycleBind
import com.example.domain.agregates.Place
import com.example.domain.mappers.Mapper

class PlaceBindToPlace : Mapper<PlaceBind, Place> {

    override fun map(input: PlaceBind): Place {

        return when(input) {
            is PlaceMotorCycleBind -> {
                PlaceMotorCycleBindToPlaceMotorCycle().map(input)
            }
            else -> {
                PlaceCarBindToPlaceCar().map(input as PlaceCarBind)
            }
        }
    }
}