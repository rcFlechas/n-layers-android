package com.example.domain.repositories

import com.example.domain.agregates.Place
import com.example.domain.enum.State

interface PlaceRepository {

    fun getPlacesAll(): List<Place>
    fun getPlacesAllByState(state: State): List<Place>
    fun getPlaceById(id: Long) : Place
    fun savePlace(place: Place)
    fun updatePlace(place: Place)
    fun deletePlacesAll()
}