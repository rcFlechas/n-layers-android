package com.example.adnceiba.binds

class MotorCycleBind(
    override val id: Long = 0,
    override val register: String,
    val cylinderCapacity: Int
): VehicleBind(id, register) {

    override val label: String = MOTORCYCLE

    companion object {
        private const val MOTORCYCLE = "MOTORCYCLE"
    }
}