package com.example.domain.repositories

import com.example.domain.agregates.Parking
import com.example.domain.enum.State

interface ParkingRepository {

    fun getAll(): List<Parking>
    fun getAllByState(state: State): List<Parking>
    fun getById(id: Long) : Parking
    fun save(parking: Parking): Boolean
    fun update(parking: Parking): Boolean
}