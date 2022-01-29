package com.example.adnceiba.di

import com.example.adnceiba.viewmodels.AddVehicleViewModel
import com.example.adnceiba.viewmodels.PlacesBusyViewModel
import com.example.adnceiba.viewmodels.PlacesFreeViewModel
import com.example.adnceiba.viewmodels.VehiclesViewModel
import com.example.domain.services.PlaceService
import com.example.domain.services.VehicleService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    single { PlaceService( placeRepository = get()) }
    single { VehicleService( vehicleRepository = get()) }

    viewModel { VehiclesViewModel(vehicleService = get()) }
    viewModel { PlacesBusyViewModel(placeService = get(), vehicleService = get()) }
    viewModel { PlacesFreeViewModel(placeService = get()) }
    viewModel { AddVehicleViewModel(vehicleService = get()) }
}