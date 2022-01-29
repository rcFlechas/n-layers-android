package com.example.domain.services.vehicle

import com.example.domain.entities.CarBuilder
import com.example.domain.exceptions.RegisterCharactersNotAllowedException
import com.example.domain.exceptions.RegisterLengthNotAllowedException
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class SaveVehicleServiceTest : VehicleServiceTest() {

    @Test
    fun saveVehicle_save_returnUnit() {

        //Arrange
        val vehicleId = 3L
        val vehicle3 = CarBuilder.aCar()
            .withId(vehicleId)
            .withRegister("CCC")
            .build()

        //Act
        val save = vehicleService.saveVehicle(vehicle3)

        //Assert
        MatcherAssert.assertThat(save, Matchers.`is`(Unit))
    }

    @Test
    fun saveVehicle_registerWithEmojisCharacters_returnRegisterCharactersNotAllowedException() {

        //Arrange
        //Act
        val isRegisterCharactersNotAllowedException = try {
            val vehicleId = 3L
            CarBuilder.aCar()
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
            CarBuilder.aCar()
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