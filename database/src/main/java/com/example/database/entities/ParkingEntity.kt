package com.example.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.domain.enum.State
import java.util.*

@Entity(
    tableName = "parkingEntity",
    indices = [ Index( value = ["vehi_id"], unique = true)]
)
data class ParkingEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "park_id")
    val id: Long = 0L,

    @ColumnInfo(name = "park_in_date")
    val inDate: Date = Date(),

    @ColumnInfo(name = "park_out_date")
    val outDate: Date = Date(),

    @ColumnInfo(name = "park_state")
    val state: State = State.IN,

    @ColumnInfo(name = "vehi_id")
    val vehicleId: Long = 0L
)
