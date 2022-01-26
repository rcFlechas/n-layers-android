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
    var id: Long = 0L,

    @ColumnInfo(name = "plac_busy_date")
    var busyDate: Date = Date(),

    @ColumnInfo(name = "plac_free_date")
    var freeDate: Date = Date(),

    @ColumnInfo(name = "plac_state")
    var state: State = State.BUSY,

    @ColumnInfo(name = "vehi_id")
    var vehicleId: Long = 0L
)
