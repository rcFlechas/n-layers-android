package com.example.database.datasources

import com.example.data.datasources.VehicleLocalDataSource
import com.example.database.dao.VehicleDAO
import com.example.database.mappers.VehicleEntityToVehicle
import com.example.database.mappers.VehicleToVehicleEntity
import com.example.domain.entities.Vehicle
import com.example.domain.mappers.ListMapperImpl

class VehicleRoomDataSource(private val vehicleDAO: VehicleDAO) : VehicleLocalDataSource {

    override fun getVehiclesAll(): List<Vehicle> =
        ListMapperImpl(VehicleEntityToVehicle())
            .map(vehicleDAO.getVehiclesAll())

    override fun getVehicleById(id: Long): Vehicle =
        VehicleEntityToVehicle()
            .map(vehicleDAO.getVehicleById(id))

    override fun saveVehicle(vehicle: Vehicle) =
        vehicleDAO.saveVehicle(VehicleToVehicleEntity().map(vehicle))
}