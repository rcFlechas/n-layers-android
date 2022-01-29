package com.example.adnceiba.ui.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.adnceiba.R
import com.example.adnceiba.utilities.EspressoIdlingResource
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun addVehicle() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val registerVehicle = "BBB"

        onView(withId(R.id.navVehiclesFragment)).perform(click())
        onView(withId(R.id.fabAddVehicle)).perform(click())

        onView(withId(R.id.add_register_edit_text))
            .perform(typeText(registerVehicle), closeSoftKeyboard())

        onView(withId(R.id.option_motorcycle))
            .perform(click())
            .check(matches(isChecked()))

        onView(withId(R.id.add_cylinder_capacity_text_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_cylinder_capacity_text_view))
            .check(matches(withText(R.string.title_add_cylinder_capacity)))
        onView(withId(R.id.add_cylinder_capacity_edit_text))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_cylinder_capacity_edit_text))
            .perform(typeText("600"), closeSoftKeyboard())
        onView(withId(R.id.save_vehicle_button)).perform(click())

        onView(withText(registerVehicle)).check(matches(isDisplayed()))
        activityScenario.close()
    }

    @Test
    fun addPlaceBusy() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.navPlacesBusyFragment)).perform(click())
        onView(withId(R.id.fabAddPlace)).perform(click())

        onData(anything())
            .atPosition(0)
            .perform(click())

        onView(withText("BBB")).check(matches(isDisplayed()))
        activityScenario.close()
    }
}