package com.example.adnceiba.binds

import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy

class PlaceMotorCycleBind(
    override val id: Long,
    override val vehicle: MotorCycleBind,
    override val timeBusy: TimeBusy,
    override var state: State,
    override val totalPay: String
) : PlaceBind()