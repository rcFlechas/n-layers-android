package com.example.adnceiba.ui.activities

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.adnceiba.di.androidTestModule
import com.example.adnceiba.pages.*
import com.example.adnceiba.utilities.EspressoIdlingResource
import com.example.domain.builders.CarBuilder.Companion.aCar
import com.example.domain.builders.MotorCycleBuilder.Companion.aMotorCycle
import com.example.domain.builders.PlaceCarBuilder.Companion.aPlaceCar
import com.example.domain.builders.PlaceMotorCycleBuilder.Companion.aPlaceMotorCycle
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.services.PlaceService
import com.example.domain.services.VehicleService
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

        val mainPage = MainPage()
        mainPage
            .launch() //WHEN - launch Main Activity
            .on<NavigationBottomPage>()
            .vehiclesTab() //and perform click on tab Vehicles
            .on<VehiclesPage>()
            .fabButtonClick() //and perform click on button Add Vehicle
            .on<AddVehiclePage>()
            .typeTextRegister("BBB") //and enter register in the form
            .checkMotorCycleRadioButton() //and checked motorcycle radio button in the form
            .typeTextCylinderCapacity("600") //and enter cylinder capacity in the form
            .saveButtonClick() //and perform click on button Save
            .on<VehiclesPage>()
            .withTitle("BBB") //THEN - check item Vehicle with text BBB.
        mainPage
            .close()
    }

    @Test
    fun addBusyPlace() {

        //GIVEN -  a vehicle type motorcycle
        val vehicle = aMotorCycle()
            .withRegister("BBB")
            .withCylinderCapacity(600)
            .build()
        vehicleService.saveVehicle(vehicle)

        val mainPage = MainPage()
        mainPage
            .launch() //WHEN - launch Main Activity
            .on<NavigationBottomPage>()
            .placesBusyTab() //and perform click on tab places busy
            .on<PlacesBusyPage>()
            .fabButtonClick() //and perform click on button Add Place
            .on<DialogPage>()
            .verify("Vehicles")
            .selectPosition(0) //and perform click at position in dialog single selection
            .on<PlacesBusyPage>()
            .withTitle("BBB") //THEN - check view place with Vehicle text BBB.
        mainPage
            .close()
    }

    @Test
    fun addFreePlace() {

        //GIVEN - a busy place of motorcycle
        val register = "BBB"

        val insertVehicle = aMotorCycle()
            .withRegister(register)
            .withCylinderCapacity(600)
            .build()
        vehicleService.saveVehicle(insertVehicle)
        val vehicle = vehicleService.getVehiclesAll().firstOrNull()

        val place = aPlaceMotorCycle()
            .withVehicle(vehicle as MotorCycle)
            .build()
        placeService.entry(place)

        val mainPage = MainPage()
        mainPage
            .launch() //WHEN - launch Main Activity
            .on<NavigationBottomPage>()
            .placesBusyTab() //and perform click on tab places busy
            .on<PlacesBusyPage>()
            .longClick(register) //and perform long click on first item list
            .on<DialogPage>()
            .selectPosition(0) //and perform click on item context menu with text Free
            .on<PlacesBusyPage>()
            .isListEmpty() //THEN - check view displayed List is Empty.
            .on<NavigationBottomPage>()
            .placesFreeTab() //and perform click on tab places free
            .on<PlacesFreePage>()
            .withTitle("BBB") //and - check view place with Vehicle text BBB.
        mainPage
            .close()
    }

    @Test
    fun addBusyPlace_notPlaceAvailable() {

        //GIVEN - 20 vehicles type cars and 20 places type car.
        for (i in 1..PlaceService.MAX_CARS) {
            val insertVehicle = aCar()
                .withId(i.toLong())
                .withRegister("CAR$i")
                .build()
            vehicleService.saveVehicle(insertVehicle)
            val vehicle = vehicleService.getVehicleById(i.toLong())

            val place = aPlaceCar()
                .withVehicle(vehicle as Car)
                .build()
            placeService.entry(place)
        }

        // and 20 vehicles type motorcycles and 10 places type motorcycle.
        for (i in (PlaceService.MAX_CARS +1)..(PlaceService.MAX_CARS + PlaceService.MAX_MOTORCYCLE)) {
            val insertVehicle = aMotorCycle()
                .withId(i.toLong())
                .withRegister("MOT$i")
                .withCylinderCapacity(100)
                .build()
            vehicleService.saveVehicle(insertVehicle)
            val vehicle = vehicleService.getVehicleById(i.toLong())

            val place = aPlaceMotorCycle()
                .withVehicle(vehicle as MotorCycle)
                .build()
            placeService.entry(place)
        }

        // and one more vehicle type motorcycle
        val register = "BBB"
        val insertVehicle = aMotorCycle()
            .withRegister(register)
            .withCylinderCapacity(600)
            .build()
        vehicleService.saveVehicle(insertVehicle)

        val mainPage = MainPage()
        mainPage
            .launch() //WHEN - launch Main Activity
            .on<NavigationBottomPage>()
            .placesBusyTab() //and perform click on tab places busy
            .on<PlacesBusyPage>()
            .fabButtonClick() //and perform click on button Add Place

            .on<DialogPage>()
            .verify("Vehicles")
            .selectPosition(30) //and perform click at last position in dialog single selection
            .on<DialogPage>()
            .verify("There is no place available.") //THEN - check view is dialog and contains message "There is no place available".
        mainPage
            .close()
    }
}