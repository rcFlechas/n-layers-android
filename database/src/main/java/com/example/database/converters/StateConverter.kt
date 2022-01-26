package com.example.database.converters

import com.example.domain.enum.State

class StateConverter {

    fun fromString(state: String): State {
        return State.valueOf(state)
    }

    fun toString(state: State): String {
        return state.name
    }
}