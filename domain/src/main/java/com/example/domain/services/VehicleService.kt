package com.example.domain.services

import com.example.domain.entities.Vehicle
import com.example.domain.repositories.VehicleRepository

class VehicleService (private val vehicleRepository: VehicleRepository) {

    fun getAll(): List<Vehicle> = vehicleRepository.getAll()
    fun getById(id: Long) : Vehicle = vehicleRepository.getById(id)
    fun save(vehicle: Vehicle): Boolean = vehicleRepository.save(vehicle)
}