package com.example.adnceiba.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.adnceiba.R

class NavigationBottomPage : Page() {

    fun placesBusyTab(): Page {

        onView(withId(R.id.navPlacesBusyFragment))
            .perform(click())
        return this
    }

    fun placesFreeTab(): Page {

        onView(withId(R.id.navPlacesFreeFragment))
            .perform(click())
        return this
    }

    fun vehiclesTab(): Page {

        onView(withId(R.id.navVehiclesFragment))
            .perform(click())
        return this
    }
}