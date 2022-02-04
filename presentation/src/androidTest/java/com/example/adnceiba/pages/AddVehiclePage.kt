package com.example.adnceiba.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.adnceiba.R

class AddVehiclePage : Page() {

    fun typeTextRegister(text: String): AddVehiclePage {

        onView(withId(R.id.add_register_edit_text))
            .perform(typeText(text), closeSoftKeyboard())
        return this
    }

    fun typeTextCylinderCapacity(text: String): AddVehiclePage {

        onView(withId(R.id.add_cylinder_capacity_edit_text))
            .perform(typeText(text), closeSoftKeyboard())
        return this
    }

    fun checkCarRadioButton(): AddVehiclePage {

        onView(withId(R.id.option_car))
            .perform(click())
        return this
    }

    fun checkMotorCycleRadioButton(): AddVehiclePage {

        onView(withId(R.id.option_motorcycle))
            .perform(click())
        return this
    }

    fun isCheckedCarRadioButton(): AddVehiclePage {

        onView(withId(R.id.option_car))
            .check(matches(isChecked()))
        return this
    }

    fun isCheckedMotorCycleRadioButton(): AddVehiclePage {

        onView(withId(R.id.option_motorcycle))
            .check(matches(isChecked()))
        return this
    }


    fun saveButtonClick(): Page {

        onView(withId(R.id.save_vehicle_button))
            .perform(click())
        return this
    }
}