package com.example.domain.repositories

import com.example.domain.entities.Vehicle


interface VehicleRepository {

    fun getVehiclesAll(): List<Vehicle>
    fun getVehicleById(id: Long) : Vehicle
    fun saveVehicle(vehicle: Vehicle)
    fun deleteVehiclesAll()
}