package com.example.adnceiba.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.mappers.VehicleBindToVehicle
import com.example.adnceiba.ui.UIState
import com.example.domain.services.VehicleService
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AddVehicleViewModel(private val vehicleService: VehicleService) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _saveLiveData = MutableLiveData<Event<UIState<Boolean>>>()
    val saveLiveData: LiveData<Event<UIState<Boolean>>> = _saveLiveData

    fun save(vehicleBind: VehicleBind) {

        subscriptions.add(
            Completable.fromCallable { vehicleService.saveVehicle(VehicleBindToVehicle().map(vehicleBind)) }
                .doOnSubscribe {
                    _saveLiveData.postValue(Event(UIState.OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        _saveLiveData.postValue(
                            Event(UIState.OnSuccess( true))
                        )
                    },
                    onError = {
                        _saveLiveData.postValue(Event( UIState.OnError(it.message ?: "Error" )))
                    }
                )
        )
    }

    fun closeSubscriptions() {
        subscriptions.dispose()
    }
}