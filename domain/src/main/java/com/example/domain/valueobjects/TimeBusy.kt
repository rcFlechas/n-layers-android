package com.example.domain.valueobjects

import com.example.common.extensions.differenceInDaysWithHours
import com.example.common.extensions.differenceInHours
import java.util.*

class TimeBusy(
    private val busyDate: Date,
    private val freeDate: Date,
) {

    val hours : Double
        get() = busyDate.differenceInHours(freeDate)

    val daysWithHours : Pair<Double, Double>
        get() = busyDate.differenceInDaysWithHours(freeDate)
}