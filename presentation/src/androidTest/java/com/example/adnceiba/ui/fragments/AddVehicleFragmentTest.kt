package com.example.adnceiba.ui.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.adnceiba.R
import org.hamcrest.core.IsNot.not
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddVehicleFragmentTest {

    @Test
    fun generalView_DisplayedInUi() {
        // WHEN - Add Vehicle fragment launched
        launchFragmentInContainer<AddVehicleFragment>(null, R.style.Theme_ADNCeiba)

        // THEN - Add Vehicle view are displayed on the screen
        // make sure that register component shown and correct
        onView(withId(R.id.add_register_text_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_register_text_view))
            .check(matches(withText(R.string.title_add_register)))
        onView(withId(R.id.add_register_edit_text))
            .check(matches(isDisplayed()))

        // and make sure that vehicle type component shown and correct
        onView(withId(R.id.add_type_text_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_type_text_view))
            .check(matches(withText(R.string.title_add_type_vehicle)))
        onView(withId(R.id.option_car))
            .check(matches(isDisplayed()))
        onView(withId(R.id.option_car))
            .check(matches(withText(R.string.title_add_type_vehicle_car)))
        onView(withId(R.id.option_car))
            .check(matches(not(isChecked())))
        onView(withId(R.id.option_motorcycle))
            .check(matches(isDisplayed()))
        onView(withId(R.id.option_motorcycle))
            .check(matches(withText(R.string.title_add_type_vehicle_motorcycle)))
        onView(withId(R.id.option_motorcycle))
            .check(matches(not(isChecked())))

        // and make sure that cylinder capacity component shown and correct
        onView(withId(R.id.add_cylinder_capacity_text_view))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.add_cylinder_capacity_edit_text))
            .check(matches(not(isDisplayed())))

        // and make sure that save button shown and correct
        onView(withId(R.id.save_vehicle_button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.save_vehicle_button))
            .check(matches(withText(R.string.title_save_vehicle)))
    }

    @Test
    fun clickOnRadioButtonTypeMotorcycle_DisplayedInUi() {

        // WHEN - Add Vehicle fragment launched
        launchFragmentInContainer<AddVehicleFragment>(null, R.style.Theme_ADNCeiba)

        // THEN - Add Vehicle view are displayed on the screen
        // make sure that register component shown and correct
        onView(withId(R.id.add_register_text_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_register_text_view))
            .check(matches(withText(R.string.title_add_register)))
        onView(withId(R.id.add_register_edit_text))
            .check(matches(isDisplayed()))

        // and make sure that vehicle type component shown and correct
        onView(withId(R.id.add_type_text_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_type_text_view))
            .check(matches(withText(R.string.title_add_type_vehicle)))
        onView(withId(R.id.option_car))
            .check(matches(isDisplayed()))
        onView(withId(R.id.option_car))
            .check(matches(withText(R.string.title_add_type_vehicle_car)))
        onView(withId(R.id.option_car))
            .check(matches(not(isChecked())))
        onView(withId(R.id.option_motorcycle))
            .check(matches(isDisplayed()))
        onView(withId(R.id.option_motorcycle))
            .check(matches(withText(R.string.title_add_type_vehicle_motorcycle)))
        onView(withId(R.id.option_motorcycle))
            .perform(click())
            .check(matches(isChecked()))

        // and make sure that cylinder capacity component shown and correct
        onView(withId(R.id.add_cylinder_capacity_text_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_cylinder_capacity_text_view))
            .check(matches(withText(R.string.title_add_cylinder_capacity)))
        onView(withId(R.id.add_cylinder_capacity_edit_text))
            .check(matches(isDisplayed()))
        
        // and make sure that save button shown and correct
        onView(withId(R.id.save_vehicle_button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.save_vehicle_button))
            .check(matches(withText(R.string.title_save_vehicle)))

    }
}