package com.example.domain.services

import com.example.domain.entities.Vehicle
import com.example.domain.repositories.VehicleRepository

class VehicleService (private val vehicleRepository: VehicleRepository) {

    fun getVehiclesAll(): List<Vehicle> = vehicleRepository.getVehiclesAll()
    fun getVehicleById(id: Long) : Vehicle = vehicleRepository.getVehicleById(id)

    fun saveVehicle(vehicle: Vehicle) {
        vehicleRepository.saveVehicle(vehicle)
    }
}