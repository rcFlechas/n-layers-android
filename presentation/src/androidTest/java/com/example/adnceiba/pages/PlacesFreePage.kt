package com.example.adnceiba.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.adnceiba.R

class PlacesFreePage : ListPlacePage() {

    fun fabButtonClick(): Page{

        onView(withId(R.id.fabAddPlace))
            .perform(click())
        return this
    }

    fun isListEmpty(): Page {

        onView(withText(R.string.message_list_empty))
            .check(matches(isDisplayed()))
        return this
    }
}