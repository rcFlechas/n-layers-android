package com.example.domain.services

import com.example.domain.entities.CarBuilder.Companion.aCar
import com.example.domain.entities.MotorCycleBuilder.Companion.aMotorCycle
import com.example.domain.entities.PlaceCarBuilder.Companion.aPlaceCar
import com.example.domain.entities.PlaceMotorCycleBuilder.Companion.aPlaceMotorCycle
import com.example.domain.entities.TimeBusyBuilder.Companion.aTimeBusy
import com.example.domain.enum.State
import com.example.domain.exceptions.EntryNotAuthorizedException
import com.example.domain.exceptions.NotPlaceAvailableException
import com.example.domain.exceptions.PlaceFreeException
import com.example.domain.fakes.FakePlaceRepository
import com.example.domain.services.PlaceService.Companion.MAX_CARS
import com.example.domain.services.PlaceService.Companion.MAX_MOTORCYCLE
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test

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
                    .withBusyDate("2022-01-14T14:30")
                    .withFreeDate("2022-01-14T14:30")
            )
            .withState(State.BUSY)
            .build()

        fakePlaceRepository.addPlaces(place1, place2)

        placeService = PlaceService(fakePlaceRepository)
    }

    @Test
    fun getPlacesAll_listSize_returnThree() {

        //Arrange
        val place3 = aPlaceCar()
            .withId(3)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val listSize = placeService.getPlacesAll().size

        //Assert
        assertThat(listSize, `is`(3))
    }

    @Test
    fun getPlacesAllByState_listSize_returnTwo() {

        //Arrange
        val place3 = aPlaceCar()
            .withId(3)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val listSize = placeService.getPlacesAllByState(State.BUSY).size

        //Assert
        assertThat(listSize, `is`(2))
    }

    @Test
    fun getPlaceById_placeId_returnThreeID() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val resultPlaceId = placeService.getPlaceById(placeId).id

        //Assert
        assertThat(resultPlaceId, `is`(placeId))
    }

    @Test
    fun entry_entryPlace_returnUnit() {

        //Arrange
        val place3 = aPlaceCar()
            .withId(3)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()

        //Act
        val entryPlace = placeService.entry(place3)

        //Assert
        assertThat(entryPlace, `is`(Unit))
    }

    @Test
    fun entry_isEnableToEntry_returnEntryNotAuthorizedException() {

        //Arrange
        val place3 = aPlaceCar()
            .withId(3)
            .with(
                aCar()
                    .withId(4)
                    .withRegister("ADD")
            )
            .with(
                aTimeBusy()
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
        assertThat(isEntryNotAuthorizedException, `is`(true))
    }

    @Test
    fun entry_isEntryPlaceCarAndPlaceMotorCycle_returnNotPlaceAvailableException() {

        //Arrange
        fakePlaceRepository.clearPlaces()
        var idToLong = 0L
        for (i in 1..MAX_CARS) {
            idToLong = i.toLong()
            val place = aPlaceCar()
                .withId(idToLong)
                .with(
                    aCar()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        for (i in (MAX_CARS+1)..(MAX_CARS+MAX_MOTORCYCLE)) {
            idToLong = i.toLong()
            val place = aPlaceMotorCycle()
                .withId(idToLong)
                .with(
                    aMotorCycle()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        val placeWithCar = aPlaceCar()
            .withId(idToLong)
            .with(
                aCar()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()

        val placeWithMotorCycle = aPlaceMotorCycle()
            .withId(idToLong)
            .with(
                aMotorCycle()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                aTimeBusy()
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
        assertThat(isNotPlaceAvailableException, `is`(true))
    }

    @Test
    fun entry_isEntryPlaceCarAndPlaceMotorCycle_returnNotPlaceAvailableExceptionByPlaceMotorCycle() {

        //Arrange
        fakePlaceRepository.clearPlaces()
        var idToLong = 0L
        for (i in 1 until MAX_CARS) {
            idToLong = i.toLong()
            val place = aPlaceCar()
                .withId(idToLong)
                .with(
                    aCar()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        for (i in (MAX_CARS)..(MAX_CARS+MAX_MOTORCYCLE)) {
            idToLong = i.toLong()
            val place = aPlaceMotorCycle()
                .withId(idToLong)
                .with(
                    aMotorCycle()
                        .withId(idToLong)
                        .withRegister("D$idToLong")
                )
                .with(
                    aTimeBusy()
                        .withBusyDate("2022-01-14T15:30")
                        .withFreeDate("2022-01-14T15:30")
                )
                .withState(State.BUSY)
                .build()
            fakePlaceRepository.addPlaces(place)
        }

        val placeWithCar = aPlaceCar()
            .withId(idToLong)
            .with(
                aCar()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()

        val placeWithMotorCycle = aPlaceMotorCycle()
            .withId(idToLong)
            .with(
                aMotorCycle()
                    .withId(idToLong)
                    .withRegister("D$idToLong")
            )
            .with(
                aTimeBusy()
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
        assertThat(isNotPlaceAvailableException, `is`(true))
    }

    @Test
    fun exit_exitPlace_returnUnit() {

        //Arrange
        val place3 = aPlaceCar()
            .withId(3)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T15:30")
            )
            .withState(State.BUSY)
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val exitPlace = placeService.exit(3)

        //Assert
        assertThat(exitPlace, `is`(Unit))
    }

    @Test
    fun exit_changeState_returnTrue() {

        //Arrange
        val place3 = aPlaceCar()
            .withId(3)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
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
        assertThat(changeState, `is`(true))
    }

    @Test
    fun exit_changeFreeDate_returnTrue() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
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
        assertThat(changeFreeDate, `is`(true))
    }

    @Test
    fun exit_readyFreeState_returnPlaceFreeException() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
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
        assertThat(isPlaceFreeException, `is`(true))
    }

    @Test
    fun getTotalPay_totalTwoHoursByCar_returnString2000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByCar, `is`("2000"))
    }

    @Test
    fun getTotalPay_totalOneMinutesByCar_returnString1000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-27T12:20")
                    .withFreeDate("2022-01-27T12:21")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByCar, `is`("1000"))
    }

    @Test
    fun getTotalPay_totalTwoHoursOneMinutesByCar_returnString3000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-27T12:20")
                    .withFreeDate("2022-01-27T14:21")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByCar, `is`("3000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysByCar_returnString16000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-16T15:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByCar, `is`("16000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysAndTwoHoursByCar_returnString18000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-16T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByCar, `is`("18000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysAndElevenHoursByCar_returnString24000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceCar()
            .withId(placeId)
            .with(
                aCar()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-17T02:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByCar = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByCar, `is`("24000"))
    }

    @Test
    fun getTotalPay_totalTwoHoursByMotorCycle_returnString1000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceMotorCycle()
            .withId(placeId)
            .with(
                aMotorCycle()
                    .withId(3)
                    .withRegister("DDD")
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-14T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByMotorCycle = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByMotorCycle, `is`("1000"))
    }

    @Test
    fun getTotalPay_totalTwoDaysAndTwoHoursAndCylinder600ByMotorCycle_returnString11000() {

        //Arrange
        val placeId = 3L
        val place3 = aPlaceMotorCycle()
            .withId(placeId)
            .with(
                aMotorCycle()
                    .withId(3)
                    .withRegister("DDD")
                    .withCylinderCapacity(600)
            )
            .with(
                aTimeBusy()
                    .withBusyDate("2022-01-14T15:30")
                    .withFreeDate("2022-01-16T17:30")
            )
            .build()
        fakePlaceRepository.addPlaces(place3)

        //Act
        val totalByMotorCycle = placeService.getTotalPay(placeId)

        //Assert
        assertThat(totalByMotorCycle, `is`("11000"))
    }
}