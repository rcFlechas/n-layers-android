package com.example.domain.extensions

import java.util.*
import kotlin.math.abs

infix fun Date.differenceInMinutes(date: Date): Int = (abs(this.time - date.time) / (60 * 1000)).toInt()

infix fun Date.differenceInHours(date: Date): Double = (this differenceInMinutes date ) doubleDiv 60

infix fun Date.differenceInDays(date: Date): Double = (this differenceInHours date ) / 24

infix fun Date.differenceInDaysWithHours(date: Date): Pair<Double, Double> {
    val totalHours = this differenceInHours date
    val days = totalHours / 24
    val pastHoursDay = totalHours % 24
    return Pair(days, pastHoursDay)
}