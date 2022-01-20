package com.example.domain.entities

class MotorCycleBuilder : VehicleBuilder()  {

    override var id: Long = 2
    override var register: String = "BBB"
    private var cylinderCapacity: Int = 100

    override fun withId(id: Long): MotorCycleBuilder {
        this.id = id
        return this
    }

    override fun withRegister(register: String): MotorCycleBuilder {
        this.register = register
        return this
    }

    fun withCylinderCapacity(cylinderCapacity: Int): MotorCycleBuilder {
        this.cylinderCapacity = cylinderCapacity
        return this
    }

    override fun build() = MotorCycle(id, register, cylinderCapacity)

    companion object {
        fun aMotorCycle() = MotorCycleBuilder()
    }
}