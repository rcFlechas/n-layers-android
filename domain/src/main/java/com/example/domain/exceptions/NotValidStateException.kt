package com.example.domain.exceptions

import java.lang.RuntimeException

class NotValidStateException(): RuntimeException(NOT_VALID_STATE) {

    companion object {
        private const val NOT_VALID_STATE = "State is not valid."
    }
}