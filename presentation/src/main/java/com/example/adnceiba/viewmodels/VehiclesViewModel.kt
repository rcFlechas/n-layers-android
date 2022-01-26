package com.example.adnceiba.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.mappers.VehicleToVehicleBind
import com.example.adnceiba.ui.UIState
import com.example.domain.mappers.ListMapperImpl
import com.example.domain.services.VehicleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class VehiclesViewModel(private val vehicleService: VehicleService) : ViewModel() {

    private val _allVehiclesLiveData = MutableLiveData<Event<UIState<List<VehicleBind>>>>()
    val allVehiclesLiveData: LiveData<Event<UIState<List<VehicleBind>>>> = _allVehiclesLiveData

    fun getAllVehicles() {

        _allVehiclesLiveData.postValue(Event(UIState.OnLoading(true)))
        viewModelScope.launch(Dispatchers.IO) {

            val vehicles = async { vehicleService.getVehiclesAll() }
            _allVehiclesLiveData.postValue(
                Event(UIState.OnSuccess( ListMapperImpl(VehicleToVehicleBind())
                    .map(vehicles.await())))
            )
            _allVehiclesLiveData.postValue(Event(UIState.OnLoading(false)))
        }
    }
}