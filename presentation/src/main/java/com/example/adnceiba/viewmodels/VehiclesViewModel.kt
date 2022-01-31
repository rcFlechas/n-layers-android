package com.example.adnceiba.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.mappers.VehicleToVehicleBind
import com.example.adnceiba.ui.OnError
import com.example.adnceiba.ui.OnLoading
import com.example.adnceiba.ui.OnSuccess
import com.example.adnceiba.ui.UIState
import com.example.domain.mappers.ListMapperImpl
import com.example.domain.services.VehicleService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class VehiclesViewModel(private val vehicleService: VehicleService) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _allVehiclesLiveData = MutableLiveData<Event<UIState<List<VehicleBind>>>>()
    val allVehiclesLiveData: LiveData<Event<UIState<List<VehicleBind>>>> = _allVehiclesLiveData

    fun getAllVehicles() {

        subscriptions.add(
            Single.fromCallable { vehicleService.getVehiclesAll() }
                .doOnSubscribe {
                    _allVehiclesLiveData.postValue(Event(OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        _allVehiclesLiveData.postValue(
                            Event(
                                OnSuccess( ListMapperImpl(VehicleToVehicleBind())
                                .map(it))
                            )
                        )
                    },
                    onError = {
                        _allVehiclesLiveData.postValue(Event( OnError(it.message ?: OnError.ERROR )))
                    }
                )
        )
    }

    fun closeSubscriptions() {
        subscriptions.dispose()
    }
}