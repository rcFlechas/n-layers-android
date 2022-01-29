package com.example.domain.services.place

import com.example.domain.entities.CarBuilder
import com.example.domain.entities.PlaceCarBuilder
import com.example.domain.entities.TimeBusyBuilder
import com.example.domain.enum.State
import com.example.domain.exceptions.PlaceFreeException
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class ExitPlaceServiceTest: PlaceServiceTest() {

    @Test
    fun exit_exitPlace_returnUnit() {

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
        fakePlaceRepository.addPlaces(place3)

        //Act
        val exitPlace = placeService.exit(3)

        //Assert
        MatcherAssert.assertThat(exitPlace, Matchers.`is`(Unit))
    }

    @Test
    fun exit_changeState_returnTrue() {

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
        fakePlaceRepository.addPlaces(place3)

        //Act
        val placeId = 3L
        val initState = placeService.getPlaceById(placeId).state
        placeService.exit(placeId)
        val endState = placeService.getPlaceById(placeId).state
        val changeState = (initState == State.BUSY) && (endState == State.FREE)

        //Assert
        MatcherAssert.assertThat(changeState, Matchers.`is`(true))
    }

    @Test
    fun exit_changeFreeDate_returnTrue() {

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
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val initFreeDate = placeService.getPlaceById(placeId).timeBusy.freeDate
        placeService.exit(placeId)
        val endFreeDate = placeService.getPlaceById(placeId).timeBusy.freeDate
        val changeFreeDate = initFreeDate.before(endFreeDate)

        //Assert
        MatcherAssert.assertThat(changeFreeDate, Matchers.`is`(true))
    }

    @Test
    fun exit_readyFreeState_returnPlaceFreeException() {

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
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.FREE)
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val isPlaceFreeException = try {
            placeService.exit(placeId)
            false
        } catch (placeFreeException: PlaceFreeException) {
            true
        }

        //Assert
        MatcherAssert.assertThat(isPlaceFreeException, Matchers.`is`(true))
    }
}