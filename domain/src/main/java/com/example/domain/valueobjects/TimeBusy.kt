package com.example.domain.valueobjects

import com.example.common.extensions.differenceInDaysWithHours
import com.example.common.extensions.differenceInHours
import java.util.*

class TimeBusy(
    private val inDate: Date,
    private val outDate: Date,
) {

    val hours : Double
        get() = inDate.differenceInHours(outDate)

    val daysWithHours : Pair<Double, Double>
        get() = inDate.differenceInDaysWithHours(outDate)
}