package com.example.domain.fakes

import com.example.domain.entities.Vehicle
import com.example.domain.repositories.VehicleRepository

class FakeVehicleRepository : VehicleRepository {

    private var vehiclesServiceData: LinkedHashMap<Long, Vehicle> = LinkedHashMap()

    fun addVehicles(vararg vehicles: Vehicle) {
        for (vehicle in vehicles) {
            vehiclesServiceData[vehicle.id] = vehicle
        }
    }

    fun clearPlaces() {
        vehiclesServiceData.clear()
    }

    override fun getVehiclesAll(): List<Vehicle> {
        return vehiclesServiceData.values.toList()
    }

    override fun getVehicleById(id: Long): Vehicle {
        return vehiclesServiceData.values.toList().find { it.id == id }!!
    }

    override fun saveVehicle(vehicle: Vehicle) {
        vehiclesServiceData[vehicle.id] = vehicle
    }
}