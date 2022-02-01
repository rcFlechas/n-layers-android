package com.example.adnceiba.extensions

import android.text.Editable
import android.text.TextWatcher

abstract class CustomTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        s.toString()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        s.toString()
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        s.toString()
    }
}