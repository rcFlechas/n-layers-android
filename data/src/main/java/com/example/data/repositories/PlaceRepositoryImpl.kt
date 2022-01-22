package com.example.data.repositories

import com.example.data.datasources.LocalPlaceDataSource
import com.example.domain.agregates.Place
import com.example.domain.enum.State
import com.example.domain.repositories.PlaceRepository

class PlaceRepositoryImpl(private val localPlaceDataSource: LocalPlaceDataSource): PlaceRepository {

    override fun getPlacesAll(): List<Place> = localPlaceDataSource.getPlacesAll()

    override fun getPlacesAllByState(state: State): List<Place> =
        localPlaceDataSource.getPlacesAllByState(state)

    override fun getPlaceById(id: Long): Place = localPlaceDataSource.getPlaceById(id)

    override fun savePlace(place: Place): Boolean = localPlaceDataSource.savePlace(place)

    override fun updatePlace(place: Place): Boolean =
        localPlaceDataSource.updatePlace(place.id, place.timeBusy.freeDate, place.state)
}