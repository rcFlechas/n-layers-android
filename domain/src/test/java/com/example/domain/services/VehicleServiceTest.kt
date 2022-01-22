package com.example.domain.services

import com.example.domain.entities.CarBuilder.Companion.aCar
import com.example.domain.entities.MotorCycleBuilder.Companion.aMotorCycle
import com.example.domain.exceptions.RegisterCharactersNotAllowedException
import com.example.domain.exceptions.RegisterLengthNotAllowedException
import com.example.domain.fakes.FakeVehicleRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test

class VehicleServiceTest {

    private lateinit var fakeVehicleRepository: FakeVehicleRepository

    private lateinit var vehicleService: VehicleService

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

    @Test
    fun getVehiclesAll_listSize_returnThree() {

        //Arrange
        val vehicleId = 3L
        val vehicle3 = aCar()
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
        val vehicle3 = aCar()
            .withId(vehicleId)
            .withRegister("CCC")
            .build()
        fakeVehicleRepository.addVehicles(vehicle3)

        //Act
        val resultVehicleId = vehicleService.getVehicleById(vehicleId).id

        //Assert
        MatcherAssert.assertThat(resultVehicleId, Matchers.`is`(vehicleId))
    }

    @Test
    fun saveVehicle_isSave_returnTrue() {

        //Arrange
        val vehicleId = 3L
        val vehicle3 = aCar()
            .withId(vehicleId)
            .withRegister("CCC")
            .build()

        //Act
        val isSave = vehicleService.saveVehicle(vehicle3)

        //Assert
        MatcherAssert.assertThat(isSave, Matchers.`is`(true))
    }

    @Test
    fun saveVehicle_isSave_returnFalse() {

        //Arrange
        val vehicleId = 3L
        val vehicle3 = aCar()
            .withId(vehicleId)
            .withRegister(String())
            .build()

        //Act
        val isSave = vehicleService.saveVehicle(vehicle3)

        //Assert
        MatcherAssert.assertThat(isSave, Matchers.`is`(false))
    }

    @Test
    fun saveVehicle_registerWithEmojisCharacters_returnRegisterCharactersNotAllowedException() {

        //Arrange
        //Act
        val isRegisterCharactersNotAllowedException = try {
            val vehicleId = 3L
            aCar()
                .withId(vehicleId)
                .withRegister("\uD83D\uDE00")
                .build()
            false
        } catch (registerCharactersNotAllowedException: RegisterCharactersNotAllowedException) {
            true
        }

        //Assert
        MatcherAssert.assertThat(isRegisterCharactersNotAllowedException, Matchers.`is`(true))
    }

    @Test
    fun saveVehicle_registerWithFourCharacter_returnRegisterNotAllowedException() {

        //Arrange
        //Act
        val isRegisterLengthNotAllowedException = try {
            val vehicleId = 3L
            aCar()
                .withId(vehicleId)
                .withRegister("CCCC")
                .build()
            false
        } catch (registerLengthNotAllowedException: RegisterLengthNotAllowedException) {
            true
        }

        //Assert
        MatcherAssert.assertThat(isRegisterLengthNotAllowedException, Matchers.`is`(true))
    }
}