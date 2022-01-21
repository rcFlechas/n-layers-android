package com.example.domain.services

import com.example.domain.agregates.Place
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.enum.State
import com.example.domain.exceptions.EntryNotAuthorizedException
import com.example.domain.exceptions.NotPlaceAvailableException
import com.example.domain.exceptions.PlaceFreeException
import com.example.domain.repositories.PlaceRepository
import java.util.*

class PlaceService (private val placeRepository: PlaceRepository) {

    fun entry(place: Place): Boolean {

        if (!place.isEnableToEntry()) {
            throw EntryNotAuthorizedException()
        }
        val listPlaces = getPlacesAllByState(State.BUSY)
        if (!thereArePlacesAvailable(listPlaces, place)) {
            throw NotPlaceAvailableException()
        }
        return placeRepository.savePlace(place)
    }

    fun exit(placeId: Long): Boolean {

        val place = placeRepository.getPlaceById(placeId)
        if (!place.isStateBusy()) {
            throw PlaceFreeException()
        }
        place.state = State.FREE
        place.timeBusy.freeDate = Date()
        return placeRepository.updatePlace(place)
    }

    fun getTotalPay(placeId: Long): String {
        val busyPlace = placeRepository.getPlaceById(placeId)
        return busyPlace.totalPay
    }

    fun getPlacesAll() = placeRepository.getPlacesAll()

    fun getPlacesAllByState(state: State) = placeRepository.getPlacesAllByState(state)

    fun getPlaceById(placeId: Long) = placeRepository.getPlaceById(placeId)

    private fun getPlacesSizeByCar(listPlace: List<Place>) = listPlace.filter { it.vehicle is Car }.size

    private fun getPlacesSizeByMotorCycle(listPlace: List<Place>) = listPlace.filter { it.vehicle is MotorCycle }.size

    private fun thereArePlacesAvailable(listPlace: List<Place>, place: Place): Boolean {
        return  (place.vehicle is Car && getPlacesSizeByCar(listPlace) < MAX_CARS) ||
                (place.vehicle is MotorCycle && getPlacesSizeByMotorCycle(listPlace) < MAX_MOTORCYCLE)
    }

    companion object {
        const val MAX_CARS = 20
        const val MAX_MOTORCYCLE = 10
    }
}



