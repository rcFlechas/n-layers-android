package com.example.domain.services

import com.example.domain.agregates.Place
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.enum.State
import com.example.domain.exceptions.EntryNotAuthorizedException
import com.example.domain.exceptions.NotPlaceAvailableException
import com.example.domain.exceptions.PlaceFreeException
import com.example.domain.repositories.PlaceRepository

class PlaceService (private val placeRepository: PlaceRepository) {

    fun entry(place: Place): Boolean {

        if (!place.isOauthEntry()) {
            throw EntryNotAuthorizedException()
        }
        val listPlaces = getPlacesAllByState(State.BUSY)
        if (!thereArePlacesAvailable(listPlaces, place)) {
            throw NotPlaceAvailableException()
        }
        return placeRepository.savePlace(place)
    }

    fun exit(placeId: Long): Boolean {

        val freePlace = placeRepository.getPlaceById(placeId)
        if (!freePlace.isStateBusy()) {
            throw PlaceFreeException()
        }
        freePlace.state = State.FREE
        return placeRepository.updatePlace(freePlace)
    }

    fun getTotalPay(placeId: Long): String {

        val freePlace = placeRepository.getPlaceById(placeId)
        return freePlace.totalPay
    }

    fun getPlacesAll() = placeRepository.getPlacesAll()

    fun getPlacesAllByState(state: State) = placeRepository.getPlacesAllByState(state)

    fun getPlaceById(placeId: Long) = placeRepository.getPlaceById(placeId)

    private fun getSizeByCar(listPlace: List<Place>) = listPlace.filter { it.vehicle is Car }.size

    private fun getSizeByMotorCycle(listPlace: List<Place>) = listPlace.filter { it.vehicle is MotorCycle }.size

    private fun thereArePlacesAvailable(listPlace: List<Place>, place: Place): Boolean {
        return  (place.vehicle is Car && getSizeByCar(listPlace) < MAX_CARS) ||
                (place.vehicle is MotorCycle && getSizeByMotorCycle(listPlace) < MAX_MOTORCYCLE)
    }

    companion object {
        private const val MAX_CARS = 20
        private const val MAX_MOTORCYCLE = 10
    }
}



