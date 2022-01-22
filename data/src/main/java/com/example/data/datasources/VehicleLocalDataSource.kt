package com.example.data.datasources

import com.example.domain.entities.Vehicle

interface VehicleLocalDataSource {

    fun getVehiclesAll(): List<Vehicle>
    fun getVehicleById(id: Long) : Vehicle
    fun saveVehicle(vehicle: Vehicle): Boolean
}