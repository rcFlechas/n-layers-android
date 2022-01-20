package com.example.domain.agregates

import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.entities.Vehicle
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import java.math.BigDecimal
import java.util.*

class Place(
    val id: Long,
    val vehicle: Vehicle,
    val timeBusy: TimeBusy,
    var state: State
) {

    val totalPay: String
        get() {
            return if (vehicle is Car) {
                this.calculateTotalPayByCar().toPlainString()
            } else {
                this.calculateTotalPayByMotorCycle().toPlainString()
            }
        }

    fun isStateBusy(): Boolean = (state == State.BUSY)

    fun isOauthEntry(): Boolean {
        val character = vehicle.register[0]
        return when {
            (character != FIRST_REGISTER_LETTER) -> true
            (character == FIRST_REGISTER_LETTER && isValidDay()) -> true
            else -> false
        }
    }

    private fun calculateTotalPayByCar(): BigDecimal {

        val hoursTimeBusy = timeBusy.hours
        val daysWithHoursTimeBusy = timeBusy.daysWithHours
        val total = when {
            (vehicle is Car && hoursTimeBusy < NINE_HOURS) -> {
                COST_BY_HOUR_CAR * hoursTimeBusy
            }
            (vehicle is Car && hoursTimeBusy in NINE_HOURS..TWENTY_FOUR_HOURS) -> {
                COST_BY_DAY_CAR.toDouble()
            }
            (vehicle is Car && hoursTimeBusy > TWENTY_FOUR_HOURS) -> {
                (COST_BY_DAY_CAR.toDouble() * daysWithHoursTimeBusy.first) + (COST_BY_HOUR_CAR * daysWithHoursTimeBusy.second)
            }
            else -> 0.0
        }
        return BigDecimal(total)
    }

    private fun calculateTotalPayByMotorCycle(): BigDecimal {

        val hoursTimeBusy = timeBusy.hours
        val daysWithHoursTimeBusy = timeBusy.daysWithHours
        var total = when {
            (vehicle is MotorCycle && hoursTimeBusy < NINE_HOURS) -> {
                COST_BY_HOUR_MOTORCYCLE * hoursTimeBusy
            }
            (vehicle is MotorCycle && hoursTimeBusy in NINE_HOURS..TWENTY_FOUR_HOURS) -> {
                COST_BY_DAY_MOTORCYCLE.toDouble()
            }
            (vehicle is MotorCycle && hoursTimeBusy > TWENTY_FOUR_HOURS) -> {
                (COST_BY_DAY_MOTORCYCLE.toDouble() * daysWithHoursTimeBusy.first) + (COST_BY_HOUR_MOTORCYCLE * daysWithHoursTimeBusy.second)
            }
            else -> 0.0
        }
        val motorCycle = vehicle as MotorCycle
        if (motorCycle.cylinderCapacity > CYLINDER_CAPACITY) total+= COST_PLUS_BY_CYLINDER_CAPACITY

        return BigDecimal(total)
    }

    private fun isValidDay(): Boolean {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        return currentDay == Calendar.SUNDAY || currentDay == Calendar.MONDAY
    }

    companion object {
        private const val FIRST_REGISTER_LETTER = 'A'
        private const val COST_BY_HOUR_CAR = 1000
        private const val COST_BY_HOUR_MOTORCYCLE = 500
        private const val COST_BY_DAY_CAR = 8000
        private const val COST_BY_DAY_MOTORCYCLE = 4000
        private const val TWENTY_FOUR_HOURS = 24.0
        private const val NINE_HOURS = 9.0
        private const val CYLINDER_CAPACITY = 500
        private const val COST_PLUS_BY_CYLINDER_CAPACITY = 2000
    }
}
