package com.example.domain.services.place

import com.example.domain.builders.CarBuilder
import com.example.domain.builders.PlaceCarBuilder
import com.example.domain.builders.TimeBusyBuilder
import com.example.domain.enum.State
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class GetPlaceServiceTest: PlaceServiceTest() {

    @Test
    fun getPlacesAll_listSize_returnThree() {

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
        val listSize = placeService.getPlacesAll().size

        //Assert
        MatcherAssert.assertThat(listSize, Matchers.`is`(3))
    }

    @Test
    fun getPlacesAllByState_listSize_returnTwo() {

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
        val listSize = placeService.getPlacesAllByState(State.BUSY).size

        //Assert
        MatcherAssert.assertThat(listSize, Matchers.`is`(2))
    }

    @Test
    fun getPlaceById_placeId_returnThreeID() {

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
        val resultPlaceId = placeService.getPlaceById(placeId).id

        //Assert
        MatcherAssert.assertThat(resultPlaceId, Matchers.`is`(placeId))
    }
}