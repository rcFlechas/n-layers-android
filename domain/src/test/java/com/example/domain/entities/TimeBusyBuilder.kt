package com.example.domain.entities

import com.example.domain.valueobjects.TimeBusy
import java.util.*

class TimeBusyBuilder {

    private var busyDate: Date = Date()
    private var freeDate: Date = Date()

    fun withBusyDate(busyDate: Date): TimeBusyBuilder {
        this.busyDate = busyDate
        return this
    }

    fun withFreeDate(freeDate: Date): TimeBusyBuilder {
        this.freeDate = freeDate
        return this
    }

    fun build() = TimeBusy(busyDate, freeDate)

    companion object {
        fun aTimeBusy() = TimeBusyBuilder()
    }
}