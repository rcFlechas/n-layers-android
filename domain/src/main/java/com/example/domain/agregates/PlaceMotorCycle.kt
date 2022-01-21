package com.example.domain.agregates

import com.example.domain.entities.MotorCycle
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import java.math.BigDecimal

class PlaceMotorCycle(
    override val id: Long,
    override val vehicle: MotorCycle,
    override val timeBusy: TimeBusy,
    override var state: State
) : Place(id, vehicle, timeBusy, state) {

    override val totalPay: String
        get() = this.calculateTotalPay(COST_BY_HOUR, COST_BY_DAY).toPlainString()

    override fun calculateTotalPay(costByHour: Int, costByDay: Int): BigDecimal {

        val total = super.calculateTotalPay(costByHour, costByDay)
        if (isMaxCylinderCapacity()) {
            total.add(COST_PLUS_BY_CYLINDER_CAPACITY.toBigDecimal())
        }
        return total
    }

    private fun isMaxCylinderCapacity(): Boolean {
        return vehicle.cylinderCapacity > CYLINDER_CAPACITY
    }

    companion object {
        private const val COST_BY_HOUR = 500
        private const val COST_BY_DAY = 4000
        private const val CYLINDER_CAPACITY = 500
        private const val COST_PLUS_BY_CYLINDER_CAPACITY = 2000
    }
}