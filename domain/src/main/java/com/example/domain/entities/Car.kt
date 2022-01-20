package com.example.domain.entities

data class Car(
    override var id: Long,
    override var register: String
) : Vehicle(id, register)
