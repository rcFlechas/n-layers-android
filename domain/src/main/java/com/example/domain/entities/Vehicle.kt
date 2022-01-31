package com.example.domain.entities

import com.example.domain.exceptions.RegisterLengthNotAllowedException
import com.example.domain.exceptions.RegisterCharactersNotAllowedException

open class Vehicle(open var id: Long, register: String) {

    open var register: String = String()

    init {

        if (!isValidCharactersRegister(register)) {
            throw RegisterCharactersNotAllowedException()
        }

        if (!isValidLengthRegister(register)) {
            throw RegisterLengthNotAllowedException()
        }

        this.register = register
    }

    private fun isValidCharactersRegister(register: String): Boolean {
        val regex = Regex(CHARACTER_REGISTER_REGEX)
        return register.contains(regex)
    }

    private fun isValidLengthRegister(register: String): Boolean {
        return register.length <= REGISTER_LENGTH
    }

    companion object {
        private const val CHARACTER_REGISTER_REGEX = "^$|^[a-zA-Z0-9]+$"
        const val REGISTER_LENGTH = 6
    }
}