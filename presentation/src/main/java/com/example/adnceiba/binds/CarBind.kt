package com.example.adnceiba.binds

class CarBind(
    override val id: Long = 0,
    override val register: String
) : VehicleBind() {

    override val label: String = CAR

    companion object {
        private const val CAR = "CAR"
    }
}
