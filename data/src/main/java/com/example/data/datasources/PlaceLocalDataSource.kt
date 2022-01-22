package com.example.data.datasources

import com.example.domain.agregates.Place
import com.example.domain.enum.State

interface PlaceLocalDataSource {

    fun getPlacesAll(): List<Place>
    fun getPlacesAllByState(state: State): List<Place>
    fun getPlaceById(id: Long) : Place
    fun savePlace(place: Place): Boolean
    fun updatePlace(place: Place): Boolean
}