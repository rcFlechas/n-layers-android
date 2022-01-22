package com.example.domain.exceptions

import java.lang.RuntimeException

class RegisterCharactersNotAllowedException : RuntimeException(REGISTER_CHARACTERS_NOT_ALLOWED) {

    companion object {
        private const val REGISTER_CHARACTERS_NOT_ALLOWED = "Register characters not allowed."
    }
}