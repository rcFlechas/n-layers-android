package com.example.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.domain.enum.State
import java.util.*

@Entity(
    tableName = "place",
    indices = [ Index( value = ["vehi_id"], unique = true)]
)
data class PlaceEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plac_id")
    val id: Long = 0L,

    @ColumnInfo(name = "plac_busy_date")
    val busyDate: Date = Date(),

    @ColumnInfo(name = "plac_free_date")
    val freeDate: Date = Date(),

    @ColumnInfo(name = "plac_state")
    val state: State = State.BUSY,

    @ColumnInfo(name = "vehi_id")
    val vehicleId: Long = 0L
)
