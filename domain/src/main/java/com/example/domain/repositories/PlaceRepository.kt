package com.example.domain.repositories

import com.example.domain.agregates.Place
import com.example.domain.enum.State

interface PlaceRepository {

    fun getAll(): List<Place>
    fun getAllByState(state: State): List<Place>
    fun getById(id: Long) : Place
    fun save(place: Place): Boolean
    fun update(place: Place): Boolean
}