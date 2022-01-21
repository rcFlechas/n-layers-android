package com.example.domain.entities

import com.example.domain.agregates.PlaceCar
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import java.util.*

class PlaceCarBuilder : PlaceBuilder() {

    override var id: Long = 1
    override var vehicle: Vehicle = CarBuilder.aCar().build()
    override var timeBusy: TimeBusy = TimeBusyBuilder.aTimeBusy()
        .withBusyDate(Date(2022, 1, 14, 13, 30))
        .withFreeDate(Date(2022, 1, 14, 14, 0))
        .build()
    override var state: State = State.FREE

    override fun withId(id: Long): PlaceCarBuilder {
        this.id = id
        return this
    }

    override fun withVehicle(vehicle: Vehicle): PlaceCarBuilder {
        this.vehicle = vehicle
        return this
    }

    override fun withTimeBusy(timeBusy: TimeBusy): PlaceCarBuilder {
        this.timeBusy = timeBusy
        return this
    }

    override fun withState(state: State): PlaceCarBuilder {
        this.state = state
        return this
    }

    override fun with(vehicleBuilder: VehicleBuilder): PlaceCarBuilder {
        this.vehicle = vehicleBuilder.build()
        return this
    }

    override fun with(timeBusyBuilder: TimeBusyBuilder): PlaceCarBuilder {
        this.timeBusy = timeBusyBuilder.build()
        return this
    }

    override fun build() = PlaceCar(id, vehicle, timeBusy, state)

    companion object {
        fun aPlaceCar() = PlaceCarBuilder()
    }
}