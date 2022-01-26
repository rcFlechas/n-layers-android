package com.example.adnceiba.mappers

import com.example.adnceiba.binds.CarBind
import com.example.domain.entities.Car
import com.example.domain.mappers.Mapper

class CarBindToCar : Mapper<CarBind, Car> {

    override fun map(input: CarBind): Car = with(input) {
        Car(id, register)
    }
}