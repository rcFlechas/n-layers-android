package com.example.adnceiba.pages

import androidx.test.core.app.ActivityScenario
import com.example.adnceiba.ui.activities.MainActivity

class MainPage : Page(){
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    fun launch(): Page {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        return this
    }

    fun close(): MainPage {
        activityScenario.close()
        return this
    }
}