package com.example.domain.builders

import com.example.domain.agregates.Place
import com.example.domain.entities.Vehicle
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy

abstract class PlaceBuilder {

    open  var id: Long = 0
    open lateinit var vehicle: Vehicle
    open lateinit var timeBusy: TimeBusy
    open lateinit var state: State

    abstract fun withId(id: Long): PlaceBuilder
    abstract fun withVehicle(vehicle: Vehicle): PlaceBuilder
    abstract fun withTimeBusy(timeBusy: TimeBusy): PlaceBuilder
    abstract fun withState(state: State): PlaceBuilder
    abstract fun with(vehicleBuilder: VehicleBuilder): PlaceBuilder
    abstract fun with(timeBusyBuilder: TimeBusyBuilder): PlaceBuilder
    abstract fun build(): Place
}