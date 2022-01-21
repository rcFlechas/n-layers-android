package com.example.domain.agregates

import com.example.domain.entities.Vehicle
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy

class PlaceCar(
    override val id: Long,
    override val vehicle: Vehicle,
    override val timeBusy: TimeBusy,
    override var state: State
) : Place() {

    override val totalPay: String
        get() = this.calculateTotalPay(COST_BY_HOUR, COST_BY_DAY).toPlainString()

    companion object {
        private const val COST_BY_HOUR = 1000
        private const val COST_BY_DAY = 8000
    }
}
