package com.example.domain.services

import com.example.domain.entities.CarBuilder.Companion.aCar
import com.example.domain.entities.PlaceCarBuilder.Companion.aPlaceCar
import com.example.domain.entities.TimeBusyBuilder.Companion.aTimeBusy
import com.example.domain.enum.State
import com.example.domain.exceptions.EntryNotAuthorizedException
import com.example.domain.fakes.FakePlaceRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import java.util.*

class PlaceServiceTest {

    private lateinit var fakePlaceRepository: FakePlaceRepository

    private lateinit var placeService: PlaceService

    @Before
    fun setupService() {

        fakePlaceRepository = FakePlaceRepository()

        val place1 = aPlaceCar()
            .build()

        val place2 = aPlaceCar()
            .withId(2)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("CCC")
            )
            .with(
                aTimeBusy()
                    .withBusyDate(Date(2022, 1, 14, 14, 30))
                    .withFreeDate(Date(2022, 1, 14, 14, 30))
            )
            .withState(State.BUSY)
            .build()

        fakePlaceRepository.addPlaces(place1, place2)

        placeService = PlaceService(fakePlaceRepository)
    }

    @Test
    fun entry_isEntry_returnTrue() {

        //Arrange
        val place3 = aPlaceCar()
            .withId(3)
            .with(
                aCar()
                    .withId(4)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate(Date(2022, 1, 14, 15, 30))
                    .withFreeDate(Date(2022, 1, 14, 15, 30))
            )
            .withState(State.BUSY)
            .build()

        //Act
        val isEntry = placeService.entry(place3)

        //Assert
        assertThat(isEntry, `is`(true))
    }

    @Test
    fun entry_isSave_returnEntryNotAuthorizedException() {

        //Arrange
        val place4 = aPlaceCar()
            .withId(4)
            .with(
                aCar()
                    .withId(5)
                    .withRegister("AEE")
            )
            .with(
                aTimeBusy()
                    .withBusyDate(Date(2022, 1, 14, 15, 30))
                    .withFreeDate(Date(2022, 1, 14, 15, 30))
            )
            .withState(State.BUSY)
            .build()

        //Act
        val isEntryNotAuthorizedException = try {
            placeService.entry(place4)
            false
        } catch (entryNotAuthorizedException: EntryNotAuthorizedException) {
            true
        }

        //Assert
        assertThat(isEntryNotAuthorizedException, `is`(true))
    }
}