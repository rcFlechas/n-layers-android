package com.example.adnceiba.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adnceiba.binds.PlaceBind
import com.example.adnceiba.mappers.PlaceToPlaceBind
import com.example.adnceiba.ui.UIState
import com.example.domain.enum.State
import com.example.domain.mappers.ListMapperImpl
import com.example.domain.services.PlaceService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PlacesBusyViewModel(private val placeService: PlaceService) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _allPlacesBusyLiveData = MutableLiveData<Event<UIState<List<PlaceBind>>>>()
    val allPlacesBusyLiveData: LiveData<Event<UIState<List<PlaceBind>>>> = _allPlacesBusyLiveData

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

    fun closeSubscriptions() {
        subscriptions.dispose()
    }
}