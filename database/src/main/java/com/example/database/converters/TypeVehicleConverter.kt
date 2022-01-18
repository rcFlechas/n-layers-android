package com.example.database.converters

import androidx.room.TypeConverter
import com.example.database.enum.TypeVehicle

class TypeVehicleConverter {

    @TypeConverter
    fun fromString(typeVehicle: String): TypeVehicle {
        return TypeVehicle.valueOf(typeVehicle)
    }

    @TypeConverter
    fun toString(typeVehicle: TypeVehicle): String {
        return typeVehicle.name
    }
}