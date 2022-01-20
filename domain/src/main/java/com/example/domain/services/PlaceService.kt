package com.example.domain.services

import com.example.domain.agregates.Place
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.enum.State
import com.example.domain.exceptions.EntryNotAuthorizedException
import com.example.domain.exceptions.NotPlaceAvailableException
import com.example.domain.exceptions.PlaceOutException
import com.example.domain.repositories.PlaceRepository

class PlaceService (private val placeRepository: PlaceRepository) {

    fun entry(place: Place): Boolean {

        if (!place.isValidEntryByRegister()) {
            throw EntryNotAuthorizedException()
        }
        val listParking = getAllByState(State.IN)
        if (!isSpaceAvailable(listParking, place)) {
            throw NotPlaceAvailableException()
        }
        return placeRepository.save(place)
    }

    fun exit(placeId: Long): Boolean {

        val outParking = placeRepository.getById(placeId)
        if (!outParking.isStateIn()) {
            throw PlaceOutException()
        }
        outParking.state = State.OUT
        return placeRepository.update(outParking)
    }

    fun getTotalPay(placeId: Long): String {

        val outParking = placeRepository.getById(placeId)
        return outParking.totalPay
    }

    fun getAll() = placeRepository.getAll()

    fun getAllByState(state: State) = placeRepository.getAllByState(state)

    fun getById(placeId: Long) = placeRepository.getById(placeId)

    private fun getSizeByCar(listPlace: List<Place>) = listPlace.filter { it.vehicle is Car }.size

    private fun getSizeByMotorCycle(listPlace: List<Place>) = listPlace.filter { it.vehicle is MotorCycle }.size

    private fun isSpaceAvailable(listPlace: List<Place>, place: Place): Boolean {
        return  (place.vehicle is Car && getSizeByCar(listPlace) < MAX_CARS) ||
                (place.vehicle is MotorCycle && getSizeByMotorCycle(listPlace) < MAX_MOTORCYCLE)
    }

    companion object {
        private const val MAX_CARS = 20
        private const val MAX_MOTORCYCLE = 10
    }
}



