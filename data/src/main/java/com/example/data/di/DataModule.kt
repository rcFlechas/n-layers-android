package com.example.data.di

import com.example.data.repositories.PlaceRepositoryImpl
import com.example.data.repositories.VehicleRepositoryImpl
import com.example.domain.repositories.PlaceRepository
import com.example.domain.repositories.VehicleRepository
import org.koin.dsl.module

val dataModule = module {
    single<PlaceRepository> { PlaceRepositoryImpl( localPlaceDataSource = get()) }
    single<VehicleRepository> { VehicleRepositoryImpl(localVehicleDataSource = get()) }
}