package com.example.data.repositories

import com.example.data.datasources.PlaceLocalDataSource
import com.example.domain.agregates.Place
import com.example.domain.enum.State
import com.example.domain.repositories.PlaceRepository

class PlaceRepositoryImpl(private val placeLocalDataSource: PlaceLocalDataSource): PlaceRepository {

    override fun getPlacesAll(): List<Place> = placeLocalDataSource.getPlacesAll()

    override fun getPlacesAllByState(state: State): List<Place> =
        placeLocalDataSource.getPlacesAllByState(state)

    override fun getPlaceById(id: Long): Place = placeLocalDataSource.getPlaceById(id)

    override fun savePlace(place: Place) = placeLocalDataSource.savePlace(place)

    override fun updatePlace(place: Place) =
        placeLocalDataSource.updatePlace(place)
}