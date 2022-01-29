package com.example.adnceiba.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.adnceiba.R
import com.example.adnceiba.di.androidTestModule
import com.example.adnceiba.utilities.EspressoIdlingResource
import com.example.domain.agregates.PlaceMotorCycle
import com.example.domain.entities.MotorCycle
import com.example.domain.enum.State
import com.example.domain.services.PlaceService
import com.example.domain.services.VehicleService
import com.example.domain.valueobjects.TimeBusy
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.java.KoinJavaComponent.get

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var placeService: PlaceService
    private lateinit var vehicleService: VehicleService

    @Before
    fun init() {
        loadKoinModules(androidTestModule)
        placeService = get(PlaceService::class.java)
        vehicleService = get(VehicleService::class.java)

        placeService.deletePlacesAll()
        vehicleService.deleteVehiclesAll()
    }

    @After
    fun reset() {
        unloadKoinModules(androidTestModule)
    }

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

        //WHEN - launch Main Activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val registerVehicle = "BBB"

        //and perform click on tab Vehicles
        onView(withId(R.id.navVehiclesFragment)).perform(click())
        //and perform click on button Add Vehicle
        onView(withId(R.id.fabAddVehicle)).perform(click())

        //and enter data in the form
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

        //and perform click on button Save
        onView(withId(R.id.save_vehicle_button)).perform(click())

        //THEN - check view Vehicle with text BBB.
        onView(withText(registerVehicle)).check(matches(isDisplayed()))
        activityScenario.close()
    }

    @Test
    fun addBusyPlace() {

        //GIVEN -  a vehicle type motorcycle
        val vehicle = MotorCycle(0, "BBB", 600)
        vehicleService.saveVehicle(vehicle)

        //WHEN - launch Main Activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //and perform click on tab places busy
        onView(withId(R.id.navPlacesBusyFragment)).perform(click())

        //and perform click on button Add Place
        onView(withId(R.id.fabAddPlace)).perform(click())

        //and perform click at position in dialog single selection
        onData(anything())
            .atPosition(0)
            .perform(click())

        //THEN - check view place with Vehicle text BBB.
        onView(withText("BBB")).check(matches(isDisplayed()))
        activityScenario.close()
    }

    @Test
    fun addFreePlace() {

        //GIVEN - a busy place of motorcycle
        val register = "BBB"

        val insertVehicle = MotorCycle(0, register, 600)
        vehicleService.saveVehicle(insertVehicle)
        val vehicle = vehicleService.getVehiclesAll().firstOrNull()

        val place = PlaceMotorCycle(0, vehicle as MotorCycle, TimeBusy(), State.BUSY)
        placeService.entry(place)

        //WHEN - launch Main Activity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //and perform click on tab places busy
        onView(withId(R.id.navPlacesBusyFragment)).perform(click())

        //and perform long click on first item list
        onView(withId(R.id.place_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(register)), longClick()))

        //and perform click on item context menu with text Free
        onData(anything())
            .atPosition(0)
            .perform(click())

        //THEN - check view displayed List is Empty.
        onView(withText(R.string.message_list_empty)).check(matches(isDisplayed()))
        //and perform click on tab places free
        onView(withId(R.id.navPlacesFreeFragment)).perform(click())

        //and - check view place with Vehicle text BBB.
        onView(withText("BBB")).check(matches(isDisplayed()))
        activityScenario.close()
    }
}