package com.example.domain.services.place

import com.example.domain.entities.CarBuilder.Companion.aCar
import com.example.domain.entities.PlaceCarBuilder.Companion.aPlaceCar
import com.example.domain.entities.TimeBusyBuilder.Companion.aTimeBusy
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
                    .withBusyDate("2022-01-14T14:30")
                    .withFreeDate("2022-01-14T14:30")
            )
            .withState(State.BUSY)
            .build()

        fakePlaceRepository.addPlaces(place1, place2)

        placeService = PlaceService(fakePlaceRepository)
    }
}