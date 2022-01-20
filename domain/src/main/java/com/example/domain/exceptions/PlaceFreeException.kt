package com.example.domain.exceptions

import java.lang.RuntimeException

class PlaceFreeException(): RuntimeException(PLACE_OUT_STATE) {

    companion object {
        private const val PLACE_OUT_STATE = "Vehicle is out state."
    }
}