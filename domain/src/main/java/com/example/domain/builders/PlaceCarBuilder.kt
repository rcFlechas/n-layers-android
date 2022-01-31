package com.example.domain.builders

import com.example.domain.agregates.PlaceCar
import com.example.domain.entities.Car
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy

class PlaceCarBuilder {

    private var id: Long = 0
    private var car: Car = CarBuilder.aCar().build()
    private var timeBusy: TimeBusy = TimeBusyBuilder.aTimeBusy()
        .build()
    private var state: State = State.BUSY

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