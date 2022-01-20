package com.example.domain.entities

class MotorCycle(
    override var id: Long,
    override var register: String,
    val cylinderCapacity: Int
) : Vehicle(id, register)