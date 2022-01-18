package com.example.domain.entities

data class MotorCycle(
    override val id: Long,
    override val register: String,
    val cylinderCapacity: Int
) : Vehicle()