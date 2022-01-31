package com.example.domain.builders

import com.example.domain.entities.Vehicle


abstract class VehicleBuilder {
    protected abstract val id: Long
    protected abstract val register: String

    abstract fun withId(id: Long): VehicleBuilder
    abstract fun withRegister(register: String): VehicleBuilder
    abstract fun build(): Vehicle
}