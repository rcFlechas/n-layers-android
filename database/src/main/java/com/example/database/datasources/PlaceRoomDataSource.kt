package com.example.database.datasources

import com.example.data.datasources.PlaceLocalDataSource
import com.example.database.dao.PlaceDAO
import com.example.database.mappers.PlaceEntityToPlace
import com.example.database.mappers.PlaceToPlaceEntity
import com.example.domain.agregates.Place
import com.example.domain.enum.State
import com.example.domain.mappers.ListMapperImpl

class PlaceRoomDataSource(private val placeDAO: PlaceDAO) : PlaceLocalDataSource {

    override fun getPlacesAll(): List<Place> =
        ListMapperImpl(PlaceEntityToPlace())
            .map(placeDAO.getPlacesAll())

    override fun getPlacesAllByState(state: State): List<Place> =
        ListMapperImpl(PlaceEntityToPlace())
            .map(placeDAO.getPlacesAllByState(state.name))

    override fun getPlaceById(id: Long): Place =
        PlaceEntityToPlace()
            .map(placeDAO.getPlaceById(id))

    override fun savePlace(place: Place) =
        placeDAO.savePlace(PlaceToPlaceEntity().map(place))

    override fun updatePlace(place: Place) =
        placeDAO.updatePlace(place.id, place.timeBusy.freeDate, place.state.name)

    override fun deletePlacesAll() = placeDAO.deletePlacesAll()
}