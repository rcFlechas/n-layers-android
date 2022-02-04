package com.example.adnceiba.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.adnceiba.R

open class ListPlacePage : ItemPlacePage() {

    fun longClick(text: String): Page {

        Espresso.onView(ViewMatchers.withId(R.id.place_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText(text)), ViewActions.longClick()
                )
            )
        return this
    }
}