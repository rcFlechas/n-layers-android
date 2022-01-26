package com.example.database.dao

import androidx.room.*
import com.example.database.entities.PlaceEntity
import com.example.database.entities.PlaceWithVehicleEntity
import com.example.domain.enum.State
import java.util.*

@Dao
interface PlaceDAO {

    @Transaction
    @Query("SELECT * FROM place")
    fun getPlacesAll(): List<PlaceWithVehicleEntity>

    @Transaction
    @Query("SELECT * FROM place WHERE plac_state =:state")
    fun getPlacesAllByState(state: State): List<PlaceWithVehicleEntity>

    @Transaction
    @Query("SELECT * FROM place WHERE plac_id = :id")
    fun getPlaceById(id: Long) : PlaceWithVehicleEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlace(place: PlaceEntity): Boolean

    @Query("UPDATE place SET plac_free_date =:freeDate, plac_state =:state WHERE plac_id =:id")
    fun updatePlace(id: Long, freeDate: Date, state: State): Boolean
}