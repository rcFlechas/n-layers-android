package com.example.adnceiba.filters

import android.text.InputFilter

object CustomFilter {

    val emojiFilter =
        InputFilter { source, start, end, _, _, _ ->
            for (index in start until end) {
                val type = Character.getType(source[index])
                if (type == Character.SURROGATE.toInt() ||
                    type == Character.NON_SPACING_MARK.toInt() ||
                    type == Character.OTHER_SYMBOL.toInt()) {
                    return@InputFilter ""
                }
            }
            null
        }

    fun lengthFilter(length: Int) = InputFilter.LengthFilter(length)
}