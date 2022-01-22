package com.example.domain.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.parseDateFormat(pattern: String) : Date? = if (this.isNotEmpty()) {
    try { SimpleDateFormat(pattern, Locale.ROOT).parse(this.trim()) } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
} else null