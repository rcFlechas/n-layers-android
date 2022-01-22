package com.example.database.di

import androidx.room.Room
import com.example.data.datasources.PlaceLocalDataSource
import com.example.data.datasources.VehicleLocalDataSource
import com.example.database.DataBase
import com.example.database.datasources.PlaceRoomDataSource
import com.example.database.datasources.VehicleRoomDataSource
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(), DataBase::class.java, "database_parking").build() }
    single { get<DataBase>().placeDAO() }
    single { get<DataBase>().vehicleDAO() }

    single<PlaceLocalDataSource> { PlaceRoomDataSource( placeDAO = get()) }
    single<VehicleLocalDataSource> { VehicleRoomDataSource( vehicleDAO = get()) }
}