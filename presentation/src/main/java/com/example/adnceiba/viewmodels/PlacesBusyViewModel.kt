package com.example.adnceiba.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adnceiba.binds.PlaceBind
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.mappers.PlaceBindToPlace
import com.example.adnceiba.mappers.PlaceToPlaceBind
import com.example.adnceiba.mappers.VehicleToVehicleBind
import com.example.adnceiba.ui.UIState
import com.example.domain.enum.State
import com.example.domain.mappers.ListMapperImpl
import com.example.domain.services.PlaceService
import com.example.domain.services.VehicleService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PlacesBusyViewModel(private val placeService: PlaceService, private val vehicleService: VehicleService) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _allPlacesBusyLiveData = MutableLiveData<Event<UIState<List<PlaceBind>>>>()
    val allPlacesBusyLiveData: LiveData<Event<UIState<List<PlaceBind>>>> = _allPlacesBusyLiveData

    private val _allPlaceByIdLiveData = MutableLiveData<Event<UIState<PlaceBind>>>()
    val allPlaceByIdLiveData: LiveData<Event<UIState<PlaceBind>>> = _allPlaceByIdLiveData

    private val _saveLiveData = MutableLiveData<Event<UIState<Boolean>>>()
    val saveLiveData: LiveData<Event<UIState<Boolean>>> = _saveLiveData

    private val _allVehiclesLiveData = MutableLiveData<Event<UIState<List<VehicleBind>>>>()
    val allVehiclesLiveData: LiveData<Event<UIState<List<VehicleBind>>>> = _allVehiclesLiveData

    fun getAllPlacesBusy() {

        subscriptions.add(
            Single.fromCallable { placeService.getPlacesAllByState(State.BUSY) }
                .doOnSubscribe {
                    _allPlacesBusyLiveData.postValue(Event(UIState.OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        _allPlacesBusyLiveData.postValue(
                            Event(
                                UIState.OnSuccess( ListMapperImpl(PlaceToPlaceBind())
                                .map(it)))
                        )
                    },
                    onError = {
                        _allPlacesBusyLiveData.postValue(Event( UIState.OnError(it.message ?: "Error" )))
                    }
                )
        )
    }

    fun getPlaceById(placeId: Long) {

        subscriptions.add(
            Single.fromCallable { placeService.getPlaceById(placeId) }
                .doOnSubscribe {
                    _allPlaceByIdLiveData.postValue(Event(UIState.OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        _allPlaceByIdLiveData.postValue(
                            Event(
                                UIState.OnSuccess(PlaceToPlaceBind()
                                    .map(it)))
                        )
                    },
                    onError = {
                        _allPlaceByIdLiveData.postValue(Event( UIState.OnError(it.message ?: "Error" )))
                    }
                )
        )
    }

    fun getAllVehicles() {

        subscriptions.add(
            Single.fromCallable { vehicleService.getVehiclesAll() }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        _allVehiclesLiveData.postValue(
                            Event(UIState.OnSuccess( ListMapperImpl(VehicleToVehicleBind())
                                .map(it)))
                        )
                    },
                    onError = {
                        _allVehiclesLiveData.postValue(Event( UIState.OnError(it.message ?: "Error" )))
                    }
                )
        )
    }

    fun save(placeBind: PlaceBind) {

        subscriptions.add(
            Completable.fromCallable { placeService.entry(PlaceBindToPlace().map(placeBind)) }
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