package com.example.domain.fakes

import com.example.domain.agregates.Place
import com.example.domain.enum.State
import com.example.domain.repositories.PlaceRepository

class FakePlaceRepository: PlaceRepository {

    private var placesServiceData: LinkedHashMap<Long, Place> = LinkedHashMap()

    fun addPlaces(vararg places: Place) {
        for (place in places) {
            placesServiceData[place.id] = place
        }
    }

    fun clearPlaces() {
        placesServiceData.clear()
    }

    override fun getPlacesAll(): List<Place> {
        return placesServiceData.values.toList()
    }

    override fun getPlacesAllByState(state: State): List<Place> {
        return placesServiceData.values.toList().filter { it.state == state }
    }

    override fun getPlaceById(id: Long): Place {
        return placesServiceData.values.toList().find { it.id == id }!!
    }

    override fun savePlace(place: Place) {
        placesServiceData[place.id] = place
    }

    override fun updatePlace(place: Place) {
        placesServiceData[place.id] = place
    }

    override fun deletePlacesAll() {
        placesServiceData.clear()
    }
}