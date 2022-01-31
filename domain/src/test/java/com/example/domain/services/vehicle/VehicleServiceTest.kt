package com.example.domain.services.vehicle

import com.example.domain.builders.CarBuilder.Companion.aCar
import com.example.domain.builders.MotorCycleBuilder.Companion.aMotorCycle
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
            .withId(1)
            .withRegister("AAA")
            .build()

        val vehicle2 = aMotorCycle()
            .withId(2)
            .withRegister("BBB")
            .build()

        fakeVehicleRepository.addVehicles(vehicle1, vehicle2)

        vehicleService = VehicleService(fakeVehicleRepository)
    }
}