package com.example.database.converters

import com.example.database.enum.TypeVehicle

class TypeVehicleConverter {

    fun fromString(typeVehicle: String): TypeVehicle {
        return TypeVehicle.valueOf(typeVehicle)
    }

    fun toString(typeVehicle: TypeVehicle): String {
        return typeVehicle.name
    }
}