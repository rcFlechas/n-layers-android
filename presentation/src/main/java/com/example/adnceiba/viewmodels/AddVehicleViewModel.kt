package com.example.adnceiba.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.mappers.VehicleBindToVehicle
import com.example.adnceiba.ui.UIState
import com.example.domain.services.VehicleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddVehicleViewModel(private val vehicleService: VehicleService) : ViewModel() {

    private val _saveLiveData = MutableLiveData<Event<UIState<Boolean>>>()
    val saveLiveData: LiveData<Event<UIState<Boolean>>> = _saveLiveData

    fun save(vehicleBind: VehicleBind) {

        _saveLiveData.postValue(Event(UIState.OnLoading(true)))
        viewModelScope.launch(Dispatchers.IO) {

            val isSave = async {
                vehicleService.saveVehicle(VehicleBindToVehicle()
                    .map(vehicleBind))
            }

            _saveLiveData.postValue(
                Event(UIState.OnSuccess( isSave.await()))
            )
            _saveLiveData.postValue(Event(UIState.OnLoading(false)))
        }
    }
}