package com.example.domain.entities

import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.entities.MotorCycleBuilder.Companion.aMotorCycle
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import java.util.*

class PlaceMotorCycleBuilder : PlaceBuilder() {

    override var id: Long = 1
    override var vehicle: Vehicle = aMotorCycle().build()
    override var timeBusy: TimeBusy = TimeBusyBuilder.aTimeBusy()
        .withBusyDate(Date(2022, 1, 14, 13, 30))
        .withFreeDate(Date(2022, 1, 14, 14, 0))
        .build()
    override var state: State = State.FREE

    override fun withId(id: Long): PlaceMotorCycleBuilder {
        this.id = id
        return this
    }

    override fun withVehicle(vehicle: Vehicle): PlaceMotorCycleBuilder {
        this.vehicle = vehicle
        return this
    }

    override fun withTimeBusy(timeBusy: TimeBusy): PlaceMotorCycleBuilder {
        this.timeBusy = timeBusy
        return this
    }

    override fun withState(state: State): PlaceMotorCycleBuilder {
        this.state = state
        return this
    }

    override fun with(vehicleBuilder: VehicleBuilder): PlaceMotorCycleBuilder {
        this.vehicle = vehicleBuilder.build()
        return this
    }

    override fun with(timeBusyBuilder: TimeBusyBuilder): PlaceMotorCycleBuilder {
        this.timeBusy = timeBusyBuilder.build()
        return this
    }

    override fun build() = PlaceMotorCycle(id, vehicle, timeBusy, state)

    companion object {
        fun aPlaceMotorCycle() = PlaceMotorCycleBuilder()
    }
}