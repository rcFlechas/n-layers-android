package com.example.adnceiba.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.adnceiba.R

class VehiclesPage : ItemVehiclePage() {

    fun fabButtonClick(): Page{

        onView(withId(R.id.fabAddVehicle))
            .perform(click())
        return this
    }
}