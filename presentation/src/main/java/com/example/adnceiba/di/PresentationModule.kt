package com.example.adnceiba.di

import com.example.adnceiba.viewmodels.AddVehicleViewModel
import com.example.adnceiba.viewmodels.VehiclesViewModel
import com.example.domain.services.VehicleService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { VehiclesViewModel(VehicleService(vehicleRepository = get())) }
    viewModel { AddVehicleViewModel(VehicleService(vehicleRepository = get())) }
}