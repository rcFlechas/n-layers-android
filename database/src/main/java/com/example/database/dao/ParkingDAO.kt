package com.example.database.dao

import androidx.room.*
import com.example.database.entities.ParkingEntity
import com.example.database.entities.ParkingWithVehicleEntity
import com.example.domain.enum.State
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

@Dao
interface ParkingDAO {

    @Transaction
    @Query("SELECT * FROM parkingEntity")
    fun getAll(): Single<List<ParkingWithVehicleEntity>>

    @Transaction
    @Query("SELECT * FROM parkingEntity WHERE park_state =:state")
    fun getAllByState(state: State): Single<List<ParkingWithVehicleEntity>>

    @Query("SELECT * FROM parkingEntity WHERE park_id = :id")
    fun getById(id: Long) : Maybe<ParkingWithVehicleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(parking: ParkingEntity): Completable

    @Query("UPDATE parkingEntity SET park_out_date =:outDate, park_state =:state")
    fun update(outDate: Date, state: State): Completable
}