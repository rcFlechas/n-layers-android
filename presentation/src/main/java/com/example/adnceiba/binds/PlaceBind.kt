package com.example.adnceiba.binds

import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy

abstract class PlaceBind {
    abstract val id: Long
    abstract val vehicle: VehicleBind
    abstract val timeBusy: TimeBusy
    abstract var state: State
    abstract val totalPay: String
}