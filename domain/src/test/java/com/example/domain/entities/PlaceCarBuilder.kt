package com.example.domain.entities

import com.example.domain.agregates.PlaceCar
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import java.util.*

class PlaceCarBuilder {

    var id: Long = 1
    var car: Car = CarBuilder.aCar().build()
    var timeBusy: TimeBusy = TimeBusyBuilder.aTimeBusy()
        .withBusyDate(Date(2022, 1, 14, 13, 30))
        .withFreeDate(Date(2022, 1, 14, 14, 0))
        .build()
    var state: State = State.FREE

    fun withId(id: Long): PlaceCarBuilder {
        this.id = id
        return this
    }

    fun withVehicle(car: Car): PlaceCarBuilder {
        this.car = car
        return this
    }

    fun withTimeBusy(timeBusy: TimeBusy): PlaceCarBuilder {
        this.timeBusy = timeBusy
        return this
    }

    fun withState(state: State): PlaceCarBuilder {
        this.state = state
        return this
    }

    fun with(carBuilder: CarBuilder): PlaceCarBuilder {
        this.car = carBuilder.build()
        return this
    }

    fun with(timeBusyBuilder: TimeBusyBuilder): PlaceCarBuilder {
        this.timeBusy = timeBusyBuilder.build()
        return this
    }

    fun build() = PlaceCar(id, car, timeBusy, state)

    companion object {
        fun aPlaceCar() = PlaceCarBuilder()
    }
}