package com.example.adnceiba.mappers

import com.example.adnceiba.binds.CarBind
import com.example.domain.entities.Car
import com.example.domain.mappers.Mapper

class CarToCarBind : Mapper<Car, CarBind> {

    override fun map(input: Car): CarBind = with(input) {
        CarBind(id, register)
    }
}