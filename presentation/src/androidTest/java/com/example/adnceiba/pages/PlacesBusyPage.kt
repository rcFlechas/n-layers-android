package com.example.adnceiba.pages

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.adnceiba.R

class PlacesBusyPage : ListPlacePage() {

    fun fabButtonClick(): Page{

        Espresso.onView(ViewMatchers.withId(R.id.fabAddPlace))
            .perform(ViewActions.click())
        return this
    }

    fun isListEmpty(): Page {

        Espresso.onView(ViewMatchers.withText(R.string.message_list_empty))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return this
    }
}