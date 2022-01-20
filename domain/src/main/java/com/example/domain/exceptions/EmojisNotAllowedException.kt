package com.example.domain.exceptions

import java.lang.RuntimeException

class EmojisNotAllowedException(): RuntimeException(EMOJIS_NOT_ALLOWED) {

    companion object {
        private const val EMOJIS_NOT_ALLOWED = "Emojis not allowed."
    }
}