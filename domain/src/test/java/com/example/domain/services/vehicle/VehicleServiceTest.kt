package com.example.domain.services.vehicle

import com.example.domain.entities.CarBuilder.Companion.aCar
import com.example.domain.entities.MotorCycleBuilder.Companion.aMotorCycle
import com.example.domain.fakes.FakeVehicleRepository
import com.example.domain.services.VehicleService
import org.junit.Before

open class VehicleServiceTest {

    protected lateinit var fakeVehicleRepository: FakeVehicleRepository

    protected lateinit var vehicleService: VehicleService

    @Before
    fun setupService() {

        fakeVehicleRepository = FakeVehicleRepository()

        val vehicle1 = aCar()
            .build()

        val vehicle2 = aMotorCycle()
            .build()

        fakeVehicleRepository.addVehicles(vehicle1, vehicle2)

        vehicleService = VehicleService(fakeVehicleRepository)
    }
}