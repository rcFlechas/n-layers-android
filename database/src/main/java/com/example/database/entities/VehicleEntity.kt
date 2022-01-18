package com.example.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.database.enum.TypeVehicle

@Entity(
    tableName = "vehicleEntity",
    indices = [ Index( value = ["vehi_register"], unique = true)]
)
data class VehicleEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vehi_id")
    val id: Long,

    @ColumnInfo(name = "vehi_register")
    val register: String,

    @ColumnInfo(name = "vehi_cylinder_capacity")
    val cylinderCapacity: Int? = null,

    @ColumnInfo(name = "vehi_type")
    val typeVehicle: TypeVehicle
)
