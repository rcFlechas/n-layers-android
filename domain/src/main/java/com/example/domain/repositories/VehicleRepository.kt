package com.example.domain.repositories

import com.example.domain.entities.Vehicle


interface VehicleRepository {

    fun getAll(): List<Vehicle>
    fun getById(id: Long) : Vehicle
    fun save(vehicle: Vehicle): Boolean
}