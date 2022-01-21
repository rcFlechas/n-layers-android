package com.example.domain.valueobjects

import com.example.domain.extensions.differenceInDaysWithHours
import java.util.*

class TimeBusy(
    private val busyDate: Date,
    var freeDate: Date,
) {

    /*val days = daysWithHours.first
    val hours = daysWithHours.second

    if (hours >= NINE_HOURS) {
        days++
        hours= 0.0
    }*/

    val daysWithHours : Pair<Double, Double>
        get() = busyDate.differenceInDaysWithHours(freeDate)
}