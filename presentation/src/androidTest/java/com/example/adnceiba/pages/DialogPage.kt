package com.example.adnceiba.pages

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.anything

class DialogPage : Page() {

    fun verify(text: String): DialogPage {

        onView(withText(text))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
        return this
    }

    fun selectPosition(position: Int): Page {

        onData(anything())
            .atPosition(position)
            .perform(click())
        return this
    }
}