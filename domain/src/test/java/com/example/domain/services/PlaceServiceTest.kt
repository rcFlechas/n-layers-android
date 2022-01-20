package com.example.domain.services

import com.example.domain.agregates.Place
import com.example.domain.entities.Car
import com.example.domain.enum.State
import com.example.domain.fakes.FakePlaceRepository
import com.example.domain.valueobjects.TimeBusy
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

        val car1 = Car(id = 1, register = "AAA")
        val timeBusy1 = TimeBusy(
            inDate = Date(2022, 1, 14, 13, 30),
            outDate = Date(2022, 1, 14, 14, 0)
        )
        val place1 = Place(id = 1, vehicle = car1, timeBusy = timeBusy1, state = State.OUT)

        val car2 = Car(id = 2, register = "BBB")
        val timeBusy2 = TimeBusy(
            inDate = Date(2022, 1, 14, 14, 30),
            outDate = Date(2022, 1, 14, 14, 30)
        )
        val place2 = Place(id = 2, vehicle = car2, timeBusy = timeBusy2, state = State.IN)

        fakePlaceRepository.addPlaces(place1, place2)

        placeService = PlaceService(fakePlaceRepository)
    }

    @Test
    fun entry_isSave_returnTrue() {

        val car3 = Car(id = 2, register = "CCC")
        val timeBusy3 = TimeBusy(
            inDate = Date(2022, 1, 14, 15, 30),
            outDate = Date(2022, 1, 14, 15, 30)
        )
        val place3 = Place(id = 3, vehicle = car3, timeBusy = timeBusy3, state = State.IN)

        val result = placeService.entry(place3)

        assertThat(result, `is`(true))
    }
}