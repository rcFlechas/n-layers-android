package com.example.domain.services

import com.example.domain.agregates.Parking
import com.example.domain.entities.Car
import com.example.domain.entities.MotorCycle
import com.example.domain.repositories.ParkingRepository

class ParkingService (private val parkingRepository: ParkingRepository) {

    private val MAX_CARS = 20
    private val MAX_MOTORCYCLE = 10

    fun entry(parking: Parking): String {

        if (!parking.isValidEntryByRegister()) {
            return "Not authorized to enter."
        }

        val listParking = getAll()
        if (!isSpaceAvailable(listParking, parking)) {
            return "There is no space available."
        }

        var result = "Could not be entered."
        if (parkingRepository.save(parking)) {
            result = "Entered successfully."
        }
        return result
    }

    fun exit(parking: Parking): String {

        var result = "Total to Pay: "
        if (parkingRepository.update(parking)) {
            val outParking = parkingRepository.getById(parking.id)
            result.plus(outParking.getTotalPay())
        } else {
            result = "Output cannot be performed."
        }
        return result
    }

    private fun isSpaceAvailable(listParking: List<Parking>, parking: Parking): Boolean {
        return  (parking.vehicle is Car && getSizeByCar(listParking) < MAX_CARS) ||
                (parking.vehicle is MotorCycle && getSizeByMotorCycle(listParking) < MAX_MOTORCYCLE)
    }

    private fun getAll() = parkingRepository.getAll()

    private fun getSizeByCar(listParking: List<Parking>) = listParking.filter { it.vehicle is Car }.size

    private fun getSizeByMotorCycle(listParking: List<Parking>) = listParking.filter { it.vehicle is MotorCycle }.size
}



