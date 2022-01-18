package com.example.domain.entities

data class Car(
    override val id: Long,
    override val register: String
) : Vehicle()
