package com.example.domain.entities

import com.example.domain.agregates.Place
import com.example.domain.entities.CarBuilder.Companion.aCar
import com.example.domain.entities.TimeBusyBuilder.Companion.aTimeBusy
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import java.util.*

class PlaceBuilder {

    private var id: Long = 1
    private var vehicle: Vehicle = aCar().build()
    private var timeBusy: TimeBusy = aTimeBusy()
        .withBusyDate(Date(2022, 1, 14, 13, 30))
        .withFreeDate(Date(2022, 1, 14, 14, 0))
        .build()
    private var state: State = State.FREE

    fun withId(id: Long): PlaceBuilder {
        this.id = id
        return this
    }

    fun withVehicle(vehicle: Vehicle): PlaceBuilder {
        this.vehicle = vehicle
        return this
    }

    fun withTimeBusy(timeBusy: TimeBusy): PlaceBuilder {
        this.timeBusy = timeBusy
        return this
    }

    fun withState(state: State): PlaceBuilder {
        this.state = state
        return this
    }

    fun with(vehicleBuilder: VehicleBuilder): PlaceBuilder {
        this.vehicle = vehicleBuilder.build()
        return this
    }

    fun with(timeBusyBuilder: TimeBusyBuilder): PlaceBuilder {
        this.timeBusy = timeBusyBuilder.build()
        return this
    }

    fun build() = Place(id, vehicle, timeBusy, state)

    companion object {
        fun aPlace() = PlaceBuilder()
    }
}