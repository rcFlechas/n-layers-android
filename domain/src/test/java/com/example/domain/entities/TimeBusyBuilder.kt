package com.example.domain.entities

import com.example.domain.extensions.parseDateFormat
import com.example.domain.valueobjects.TimeBusy
import java.util.*

class TimeBusyBuilder {

    private var busyDate: Date = Date()
    private var freeDate: Date = Date()

    fun withBusyDate(busyDate: String): TimeBusyBuilder {
        this.busyDate = busyDate.parseDateFormat(TimeBusy.DATE_FORMAT) ?: Date()
        return this
    }

    fun withFreeDate(freeDate: String): TimeBusyBuilder {
        this.freeDate = freeDate.parseDateFormat(TimeBusy.DATE_FORMAT) ?: Date()
        return this
    }

    fun build() = TimeBusy(busyDate, freeDate)

    companion object {
        fun aTimeBusy() = TimeBusyBuilder()
    }
}