package com.example.domain.services.place

import com.example.domain.builders.*
import com.example.domain.enum.State
import com.example.domain.exceptions.EntryNotAuthorizedException
import com.example.domain.exceptions.NotPlaceAvailableException
import com.example.domain.services.PlaceService
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class EntryPlaceServiceTest: PlaceServiceTest() {

    @Test
    fun entry_entryPlace_returnUnit() {

        //Arrange
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(3)
            .with(
                CarBuilder.aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()

        //Act
        val entryPlace = placeService.entry(place3)

        //Assert
        MatcherAssert.assertThat(entryPlace, Matchers.`is`(Unit))
    }

    @Test
    fun entry_isEnableToEntry_returnEntryNotAuthorizedException() {

        //Arrange
        val place3 = PlaceCarBuilder.aPlaceCar()
            .withId(3)
            .with(
                CarBuilder.aCar()
                    .withId(4)
                    .withRegister("ADD")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()

        //Act
        val isEntryNotAuthorizedException = try {
            placeService.entry(place3)
            false
        } catch (entryNotAuthorizedException: EntryNotAuthorizedException) {
            true
        }

        //Assert
        MatcherAssert.assertThat(isEntryNotAuthorizedException, Matchers.`is`(true))
    }

    @Test
    fun entry_isEntryPlaceCarAndPlaceMotorCycle_returnNotPlaceAvailableException() {

        //Arrange
        fakePlaceRepository.clearPlaces()
        var idToLong = 0L
        for (i in 1..PlaceService.MAX_CARS) {
            idToLong = i.toLong()
            val place = PlaceCarBuilder.aPlaceCar()
                .withId(idToLong)
                .with(
                    CarBuilder.aCar()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    TimeBusyBuilder.aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        for (i in (PlaceService.MAX_CARS +1)..(PlaceService.MAX_CARS + PlaceService.MAX_MOTORCYCLE)) {
            idToLong = i.toLong()
            val place = PlaceMotorCycleBuilder.aPlaceMotorCycle()
                .withId(idToLong)
                .with(
                    MotorCycleBuilder.aMotorCycle()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    TimeBusyBuilder.aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        val placeWithCar = PlaceCarBuilder.aPlaceCar()
            .withId(idToLong)
            .with(
                CarBuilder.aCar()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()

        val placeWithMotorCycle = PlaceMotorCycleBuilder.aPlaceMotorCycle()
            .withId(idToLong)
            .with(
                MotorCycleBuilder.aMotorCycle()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()


        //Act
        val isNotPlaceAvailableExceptionWithPlaceCar = try {
            placeService.entry(placeWithCar)
            false
        } catch (notPlaceAvailableException: NotPlaceAvailableException) {
            true
        }

        val isNotPlaceAvailableExceptionWithPlaceMotorCycle = try {
            placeService.entry(placeWithMotorCycle)
            false
        } catch (notPlaceAvailableException: NotPlaceAvailableException) {
            true
        }
        val isNotPlaceAvailableException = isNotPlaceAvailableExceptionWithPlaceCar && isNotPlaceAvailableExceptionWithPlaceMotorCycle

        //Assert
        MatcherAssert.assertThat(isNotPlaceAvailableException, Matchers.`is`(true))
    }

    @Test
    fun entry_isEntryPlaceCarAndPlaceMotorCycle_returnNotPlaceAvailableExceptionByPlaceMotorCycle() {

        //Arrange
        fakePlaceRepository.clearPlaces()
        var idToLong = 0L
        for (i in 1 until PlaceService.MAX_CARS) {
            idToLong = i.toLong()
            val place = PlaceCarBuilder.aPlaceCar()
                .withId(idToLong)
                .with(
                    CarBuilder.aCar()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    TimeBusyBuilder.aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        for (i in (PlaceService.MAX_CARS)..(PlaceService.MAX_CARS + PlaceService.MAX_MOTORCYCLE)) {
            idToLong = i.toLong()
            val place = PlaceMotorCycleBuilder.aPlaceMotorCycle()
                .withId(idToLong)
                .with(
                    MotorCycleBuilder.aMotorCycle()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    TimeBusyBuilder.aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        val placeWithCar = PlaceCarBuilder.aPlaceCar()
            .withId(idToLong)
            .with(
                CarBuilder.aCar()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()

        val placeWithMotorCycle = PlaceMotorCycleBuilder.aPlaceMotorCycle()
            .withId(idToLong)
            .with(
                MotorCycleBuilder.aMotorCycle()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                TimeBusyBuilder.aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()


        //Act
        val isNotPlaceAvailableExceptionWithPlaceCar = try {
            placeService.entry(placeWithCar)
            false
        } catch (notPlaceAvailableException: NotPlaceAvailableException) {
            true
        }

        val isNotPlaceAvailableExceptionWithPlaceMotorCycle = try {
            placeService.entry(placeWithMotorCycle)
            false
        } catch (notPlaceAvailableException: NotPlaceAvailableException) {
            true
        }
        val isNotPlaceAvailableException = !isNotPlaceAvailableExceptionWithPlaceCar && isNotPlaceAvailableExceptionWithPlaceMotorCycle

        //Assert
        MatcherAssert.assertThat(isNotPlaceAvailableException, Matchers.`is`(true))
    }
}