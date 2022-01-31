package com.example.domain.services.vehicle

import com.example.domain.builders.CarBuilder
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class GetVehicleServiceTest : VehicleServiceTest() {

    @Test
    fun getVehiclesAll_listSize_returnThree() {

        //Arrange
        val vehicleId = 3L
        val vehicle3 = CarBuilder.aCar()
            .withId(vehicleId)
            .withRegister("CCC")
            .build()
        fakeVehicleRepository.addVehicles(vehicle3)

        //Act
        val listSize = vehicleService.getVehiclesAll().size

        //Assert
        MatcherAssert.assertThat(listSize, Matchers.`is`(3))
    }

    @Test
    fun getVehicleById_vehicleId_returnThree() {

        //Arrange
        val vehicleId = 3L
        val vehicle3 = CarBuilder.aCar()
            .withId(vehicleId)
            .withRegister("CCC")
            .build()
        fakeVehicleRepository.addVehicles(vehicle3)

        //Act
        val resultVehicleId = vehicleService.getVehicleById(vehicleId).id

        //Assert
        MatcherAssert.assertThat(resultVehicleId, Matchers.`is`(vehicleId))
    }
}