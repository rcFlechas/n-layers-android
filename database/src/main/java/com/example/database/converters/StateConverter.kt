package com.example.database.converters

import androidx.room.TypeConverter
import com.example.domain.enum.State

class StateConverter {

    @TypeConverter
    fun fromString(state: String): State {
        return State.valueOf(state)
    }

    @TypeConverter
    fun toString(state: State): String {
        return state.name
    }
}