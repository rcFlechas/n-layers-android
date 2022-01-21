package com.example.domain.valueobjects

import com.example.common.extensions.differenceInDaysWithHours
import com.example.common.extensions.differenceInHours
import java.util.*

class TimeBusy(
    private val busyDate: Date,
    private val freeDate: Date,
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