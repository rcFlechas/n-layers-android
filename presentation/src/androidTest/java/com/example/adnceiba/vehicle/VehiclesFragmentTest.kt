package com.example.adnceiba.vehicle

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.adnceiba.R
import com.example.adnceiba.ui.fragments.VehiclesFragment
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)

class VehiclesFragmentTest {

    @Test
    fun clickFabAddVehicle_navigateToAddVehicleFragment() {

        // GIVEN - Go to AddVehicleFragment
        // WHEN
        launchFragmentInContainer<VehiclesFragment>(null, R.style.Theme_ADNCeiba)
        onView(withId(R.id.fabAddVehicle)).check(matches(isDisplayed()))
        onView(withId(R.id.fabAddVehicle)).perform(click())
        onView(withId(R.id.content_add_vehicle)).check(matches(isDisplayed()))
    }
}