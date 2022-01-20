package com.example.domain.exceptions

import java.lang.RuntimeException

class EntryNotAuthorizedException : RuntimeException(ENTRY_NOT_AUTHORIZED) {

    companion object {
        private const val ENTRY_NOT_AUTHORIZED = "Entry not authorized."
    }
}