package com.example.domain.services.place

import com.example.domain.builders.*
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class GetTotalPayPlaceServiceTest : PlaceServiceTest() {

    @Test
    fun getTotalPay_totalTwoHoursByCar_returnString2000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(placeId)
            .with(
                CarBuilder.aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByCar, Matchers.`is`("2000"))
    }

    @Test
    fun getTotalPay_totalOneMinutesByCar_returnString1000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(placeId)
            .with(
                CarBuilder.aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-27T12:20")
                    .withFreeDate("2022-01-27T12:21")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByCar, Matchers.`is`("1000"))
    }

    @Test
    fun getTotalPay_totalTwoHoursOneMinutesByCar_returnString3000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(placeId)
            .with(
                CarBuilder.aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-27T12:20")
                    .withFreeDate("2022-01-27T14:21")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByCar, Matchers.`is`("3000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysByCar_returnString16000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(placeId)
            .with(
                CarBuilder.aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-16T15:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByCar, Matchers.`is`("16000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysAndTwoHoursByCar_returnString18000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(placeId)
            .with(
                CarBuilder.aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-16T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByCar, Matchers.`is`("18000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysAndElevenHoursByCar_returnString24000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(placeId)
            .with(
                CarBuilder.aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-17T02:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByCar, Matchers.`is`("24000"))
    }

    @Test
    fun getTotalPay_totalTwoHoursByMotorCycle_returnString1000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceMotorCycleBuilder.aPlaceMotorCycle()
            .withId(placeId)
            .with(
                MotorCycleBuilder.aMotorCycle()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByMotorCycle = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByMotorCycle, Matchers.`is`("1000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysAndTwoHoursAndCylinder600ByMotorCycle_returnString11000() {

        //Arrange
        val placeId = 3L
        val place3 = PlaceMotorCycleBuilder.aPlaceMotorCycle()
            .withId(placeId)
            .with(
                MotorCycleBuilder.aMotorCycle()
                    .withId(3)
                    .withRegister("DDD")
                    .withCylinderCapacity(600)
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-16T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByMotorCycle = placeService.getTotalPay(placeId)

        //Assert
        MatcherAssert.assertThat(totalByMotorCycle, Matchers.`is`("11000"))
    }
}