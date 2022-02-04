package com.example.adnceiba.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.adnceiba.R

open class ItemPlacePage : Page() {

    fun withTitle(title: String): ItemPlacePage {
        onView(withId(R.id.description_vehicle))
            .check(matches(withText(title)))
        return this
    }
}