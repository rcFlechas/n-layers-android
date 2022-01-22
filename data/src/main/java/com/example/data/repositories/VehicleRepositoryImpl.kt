package com.example.data.repositories

import com.example.data.datasources.LocalVehicleDataSource
import com.example.domain.entities.Vehicle
import com.example.domain.repositories.VehicleRepository

class VehicleRepositoryImpl(private val localVehicleDataSource: LocalVehicleDataSource): VehicleRepository {

    override fun getVehiclesAll(): List<Vehicle> = localVehicleDataSource.getVehiclesAll()

    override fun getVehicleById(id: Long): Vehicle = localVehicleDataSource.getVehicleById(id)

    override fun saveVehicle(vehicle: Vehicle): Boolean = localVehicleDataSource.saveVehicle(vehicle)
}