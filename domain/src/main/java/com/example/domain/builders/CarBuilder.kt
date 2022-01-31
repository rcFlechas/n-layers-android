package com.example.domain.builders

import com.example.domain.entities.Car


class CarBuilder : VehicleBuilder() {

    override var id: Long = 0
    override var register: String = String()

    override fun withId(id: Long): CarBuilder {
        this.id = id
        return this
    }

    override fun withRegister(register: String): CarBuilder {
        this.register = register
        return this
    }

    override fun build() = Car(id, register)

    companion object {
        fun aCar() = CarBuilder()
    }
}