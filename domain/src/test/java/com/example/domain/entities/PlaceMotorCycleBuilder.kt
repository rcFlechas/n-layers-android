package com.example.domain.entities

import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.entities.MotorCycleBuilder.Companion.aMotorCycle
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import java.util.*

class PlaceMotorCycleBuilder {

     var id: Long = 1
     var motorCycle: MotorCycle = aMotorCycle().build()
     var timeBusy: TimeBusy = TimeBusyBuilder.aTimeBusy()
        .withBusyDate(Date(2022, 1, 14, 13, 30))
        .withFreeDate(Date(2022, 1, 14, 14, 0))
        .build()
     var state: State = State.FREE

     fun withId(id: Long): PlaceMotorCycleBuilder {
        this.id = id
        return this
    }

     fun withVehicle(motorCycle: MotorCycle): PlaceMotorCycleBuilder {
        this.motorCycle = motorCycle
        return this
    }

     fun withTimeBusy(timeBusy: TimeBusy): PlaceMotorCycleBuilder {
        this.timeBusy = timeBusy
        return this
    }

     fun withState(state: State): PlaceMotorCycleBuilder {
        this.state = state
        return this
    }

     fun with(motorCycleBuilder: MotorCycleBuilder): PlaceMotorCycleBuilder {
        this.motorCycle = motorCycleBuilder.build()
        return this
    }

     fun with(timeBusyBuilder: TimeBusyBuilder): PlaceMotorCycleBuilder {
        this.timeBusy = timeBusyBuilder.build()
        return this
    }

     fun build() = PlaceMotorCycle(id, motorCycle, timeBusy, state)

    companion object {
        fun aPlaceMotorCycle() = PlaceMotorCycleBuilder()
    }
}