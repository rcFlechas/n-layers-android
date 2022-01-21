package com.example.domain.entities

import com.example.domain.agregates.Place
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy

abstract class PlaceBuilder {

    protected abstract var id: Long
    protected abstract var vehicle: Vehicle
    protected abstract var timeBusy: TimeBusy
    protected abstract var state: State

    abstract fun withId(id: Long): PlaceBuilder
    abstract fun withVehicle(vehicle: Vehicle): PlaceBuilder
    abstract fun withTimeBusy(timeBusy: TimeBusy): PlaceBuilder
    abstract fun withState(state: State): PlaceBuilder
    abstract fun with(vehicleBuilder: VehicleBuilder): PlaceBuilder
    abstract fun with(timeBusyBuilder: TimeBusyBuilder): PlaceBuilder
    abstract fun build(): Place
}