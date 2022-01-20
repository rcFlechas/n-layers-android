package com.example.domain.exceptions

import java.lang.RuntimeException

class NotPlaceAvailableException(): RuntimeException(NOT_SPACE_AVAILABLE) {

    companion object {
        private const val NOT_SPACE_AVAILABLE = "There is no place available."
    }
}