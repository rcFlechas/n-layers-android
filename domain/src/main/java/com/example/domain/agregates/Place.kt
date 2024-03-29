package com.example.domain.agregates

import com.example.domain.entities.Vehicle
import com.example.domain.enum.DayOfWeek
import com.example.domain.enum.State
import com.example.domain.extensions.dayOfWeek
import com.example.domain.valueobjects.TimeBusy
import java.math.BigDecimal

abstract class Place(
    open val id: Long,
    open val vehicle: Vehicle,
    open val timeBusy: TimeBusy,
    open var state: State
) {

    abstract val totalPay: String

    fun isStateBusy(): Boolean = (state == State.BUSY)

    fun isEnableToEntry(): Boolean {
        val character = vehicle.register[0]
        return when {
            (character != FIRST_REGISTER_LETTER) -> true
            (character == FIRST_REGISTER_LETTER && isValidDay()) -> true
            else -> false
        }
    }

    private fun isValidDay(): Boolean {
        val currentDay = timeBusy.busyDate.dayOfWeek()
        return currentDay == DayOfWeek.SUNDAY || currentDay == DayOfWeek.MONDAY
    }

    open fun calculateTotalPay(costByHour: Int, costByDay: Int): BigDecimal {

        var days = timeBusy.daysWithHours.first.toInt()
        var hours = if (isHorsNotRound() ) {
            timeBusy.daysWithHours.second.toInt() + ONE_HOUR
        } else {
            timeBusy.daysWithHours.second.toInt()
        }

        if (hours >= NINE_HOURS) {
            days++
            hours= 0
        }

        val total = (costByDay.toDouble() * days) + (costByHour.toDouble() * hours)
        return BigDecimal(total)
    }

    private fun isHorsNotRound(): Boolean {
        return (timeBusy.daysWithHours.second > 0.0) && (timeBusy.daysWithHours.second % ONE_HOUR != 0.0)
    }

    companion object {
        private const val FIRST_REGISTER_LETTER = 'A'
        private const val NINE_HOURS = 9
        private const val ONE_HOUR = 1
    }
}