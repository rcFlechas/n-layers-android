package com.example.domain.builders

import com.example.domain.entities.MotorCycle

class MotorCycleBuilder : VehicleBuilder()  {

    override var id: Long = 0
    override var register: String = String()
    private var cylinderCapacity: Int = 0

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