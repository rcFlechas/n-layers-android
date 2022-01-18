package com.example.domain.agregates

import com.example.common.extensions.differenceInDaysWithHours
import com.example.common.extensions.differenceInHours
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.entities.Vehicle
import com.example.domain.enum.State
import java.math.BigDecimal
import java.util.*

data class Parking(
    val id: Long,
    val vehicle: Vehicle,
    val inDate: Date,
    val outDate: Date,
    val state: State
) {

    fun isValidEntryByRegister(): Boolean {

        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val character = vehicle.register[0]

        return when {
            (character != FIRST_REGISTER_LETTER) -> true
            (character == FIRST_REGISTER_LETTER &&
                    (currentDay == Calendar.SUNDAY || currentDay == Calendar.MONDAY)) -> true
            else -> false
        }
    }

    val totalPay: String
        get() {
            return if (vehicle is Car) {
                this.calculateTotalPayByCar().toPlainString()
            } else {
                this.calculateTotalPayByMotorCycle().toPlainString()
            }
        }

    private fun calculateTotalPayByCar(): BigDecimal {

        val diffHours = inDate.differenceInHours(outDate)
        val diffDaysWithHours = inDate.differenceInDaysWithHours(outDate)
        val total = when {
            (vehicle is Car && diffHours < NINE_HOURS) -> {
                COST_BY_HOUR_CAR * diffHours
            }
            (vehicle is Car && diffHours in NINE_HOURS..TWENTY_FOUR_HOURS) -> {
                COST_BY_DAY_CAR.toDouble()
            }
            (vehicle is Car && diffHours > TWENTY_FOUR_HOURS) -> {
                (COST_BY_DAY_CAR.toDouble() * diffDaysWithHours.first) + (COST_BY_HOUR_CAR * diffDaysWithHours.second)
            }
            else -> 0.0
        }
        return BigDecimal(total)
    }

    private fun calculateTotalPayByMotorCycle(): BigDecimal {

        val diffHours = inDate.differenceInHours(outDate)
        val diffDaysWithHours = inDate.differenceInDaysWithHours(outDate)
        var total = when {
            (vehicle is MotorCycle && diffHours < NINE_HOURS) -> {
                COST_BY_HOUR_MOTORCYCLE * diffHours
            }
            (vehicle is MotorCycle && diffHours in NINE_HOURS..TWENTY_FOUR_HOURS) -> {
                COST_BY_DAY_MOTORCYCLE.toDouble()
            }
            (vehicle is MotorCycle && diffHours > TWENTY_FOUR_HOURS) -> {
                (COST_BY_DAY_MOTORCYCLE.toDouble() * diffDaysWithHours.first) + (COST_BY_HOUR_MOTORCYCLE * diffDaysWithHours.second)
            }
            else -> 0.0
        }
        val motorCycle = vehicle as MotorCycle
        if (motorCycle.cylinderCapacity > CYLINDER_CAPACITY) total+= COST_PLUS_BY_CYLINDER_CAPACITY

        return BigDecimal(total)
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
