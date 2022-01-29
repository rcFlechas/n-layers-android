package com.example.adnceiba.binds

import com.example.adnceiba.widgets.ItemPicker

open class VehicleBind(
    override val id: Long,
    open val register: String
): ItemPicker {
    open val label: String = String()
    override val title: String
        get() = register

}
