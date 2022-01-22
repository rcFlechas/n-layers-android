package com.example.data.datasources

import com.example.domain.agregates.Place
import com.example.domain.enum.State
import java.util.*

interface LocalPlaceDataSource {

    fun getPlacesAll(): List<Place>
    fun getPlacesAllByState(state: State): List<Place>
    fun getPlaceById(id: Long) : Place
    fun savePlace(place: Place): Boolean
    fun updatePlace(id: Long, freeDate: Date, state: State): Boolean
}