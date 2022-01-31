package com.example.domain.builders

import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.builders.MotorCycleBuilder.Companion.aMotorCycle
import com.example.domain.builders.TimeBusyBuilder.Companion.aTimeBusy
import com.example.domain.entities.MotorCycle
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy

class PlaceMotorCycleBuilder {

     private var id: Long = 0
     private var motorCycle: MotorCycle = aMotorCycle().build()
     private var timeBusy: TimeBusy = aTimeBusy()
        .build()
     private var state: State = State.BUSY

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