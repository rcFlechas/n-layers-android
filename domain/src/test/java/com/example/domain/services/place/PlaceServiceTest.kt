package com.example.domain.services.place

import com.example.domain.builders.CarBuilder.Companion.aCar
import com.example.domain.builders.PlaceCarBuilder.Companion.aPlaceCar
import com.example.domain.builders.TimeBusyBuilder.Companion.aTimeBusy
import com.example.domain.enum.State
import com.example.domain.fakes.FakePlaceRepository
import com.example.domain.services.PlaceService
import org.junit.Before

open class PlaceServiceTest {

    protected lateinit var fakePlaceRepository: FakePlaceRepository

    protected lateinit var placeService: PlaceService

    @Before
    fun setupService() {

        fakePlaceRepository = FakePlaceRepository()

        val place1 = aPlaceCar()
            .withId(1)
            .with(
                aCar()
                    .withId(1)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T13:30")
                    .withFreeDate("2022-01-14T14:00")
            )
            .withState(State.FREE)
            .build()

        val place2 = aPlaceCar()
            .withId(2)
            .with(
                aCar()
                    .withId(2)
                    .withRegister("CCC")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T14:30")
                    .withFreeDate("2022-01-14T14:30")
            )
            .withState(State.BUSY)
            .build()

        fakePlaceRepository.addPlaces(place1, place2)

        placeService = PlaceService(fakePlaceRepository)
    }
}