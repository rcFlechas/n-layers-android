package com.example.data.repositories

import com.example.data.datasources.VehicleLocalDataSource
import com.example.domain.entities.Vehicle
import com.example.domain.repositories.VehicleRepository

class VehicleRepositoryImpl(private val vehicleLocalDataSource: VehicleLocalDataSource): VehicleRepository {

    override fun getVehiclesAll(): List<Vehicle> = vehicleLocalDataSource.getVehiclesAll()

    override fun getVehicleById(id: Long): Vehicle = vehicleLocalDataSource.getVehicleById(id)

    override fun saveVehicle(vehicle: Vehicle) = vehicleLocalDataSource.saveVehicle(vehicle)

    override fun deleteVehiclesAll() = vehicleLocalDataSource.deleteVehiclesAll()
}