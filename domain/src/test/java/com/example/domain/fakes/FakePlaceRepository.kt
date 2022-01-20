package com.example.domain.fakes

import com.example.domain.agregates.Place
import com.example.domain.enum.State
import com.example.domain.repositories.PlaceRepository

class FakePlaceRepository: PlaceRepository {

    var placesServiceData: LinkedHashMap<Long, Place> = LinkedHashMap()

    fun addPlaces(vararg places: Place) {
        for (place in places) {
            placesServiceData[place.id] = place
        }
    }

    override fun getAll(): List<Place> {
        return placesServiceData.values.toList()
    }

    override fun getAllByState(state: State): List<Place> {
        return placesServiceData.values.toList().filter { it.state == state }
    }

    override fun getById(id: Long): Place {
        return placesServiceData.values.toList().find { it.id == id }!!
    }

    override fun save(place: Place): Boolean {
        placesServiceData[place.id] = place
        return placesServiceData.values.toList().any { it.id == place.id }
    }

    override fun update(place: Place): Boolean {
        placesServiceData[place.id] = place
        return placesServiceData.values.toList().any { it.id == place.id }
    }
}