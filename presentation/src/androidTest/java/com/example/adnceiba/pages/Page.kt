package com.example.adnceiba.pages

import androidx.test.espresso.Espresso

open class Page {

    inline fun <reified T : Page> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify(): Page {
        return this
    }

    fun back(): Page {
        Espresso.pressBack()
        return this
    }

    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }
}