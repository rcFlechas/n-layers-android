package com.example.adnceiba.extensions

import android.text.Editable
import android.widget.TextView

fun TextView.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : CustomTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }
    })
}