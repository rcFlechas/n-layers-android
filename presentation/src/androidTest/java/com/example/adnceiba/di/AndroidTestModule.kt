package com.example.adnceiba.di

import com.example.domain.services.PlaceService
import com.example.domain.services.VehicleService
import org.koin.dsl.module

val androidTestModule = module(override = true) {

    single { PlaceService( placeRepository = get()) }
    single { VehicleService( vehicleRepository = get()) }
}