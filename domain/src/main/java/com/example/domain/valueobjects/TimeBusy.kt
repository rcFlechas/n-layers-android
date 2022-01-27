package com.example.domain.valueobjects

import com.example.domain.extensions.differenceInDaysWithHours
import java.util.*

class TimeBusy(
    val busyDate: Date = Date(),
    var freeDate: Date = Date(),
) {

    val daysWithHours : Pair<Double, Double>
        get() = busyDate.differenceInDaysWithHours(freeDate)

    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm"
    }
}