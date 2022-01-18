package com.example.database.di

import androidx.room.Room
import com.example.database.DataBase
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(get(), DataBase::class.java, "database_parking").build() }
    single { get<DataBase>().parkingDAO() }
    single { get<DataBase>().vehicleDAO() }
}