package com.example.domain.entities


class CarBuilder : VehicleBuilder() {

    override var id: Long = 1
    override var register: String = "AAA"

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