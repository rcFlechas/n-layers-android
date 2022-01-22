package com.example.domain.exceptions

import java.lang.RuntimeException

class RegisterLengthNotAllowedException : RuntimeException(REGISTER_LENGTH_NOT_ALLOWED) {

    companion object {
        private const val REGISTER_LENGTH_NOT_ALLOWED = "Register Length not allowed."
    }
}