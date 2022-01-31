package com.example.adnceiba.viewmodels

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adnceiba.binds.PlaceBind
import com.example.adnceiba.mappers.PlaceToPlaceBind
import com.example.adnceiba.ui.OnError
import com.example.adnceiba.ui.OnLoading
import com.example.adnceiba.ui.OnSuccess
import com.example.adnceiba.ui.UIState
import com.example.domain.enum.State
import com.example.domain.mappers.ListMapperImpl
import com.example.domain.services.PlaceService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PlacesFreeViewModel(private val placeService: PlaceService) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _allPlacesFreeLiveData = MutableLiveData<Event<UIState<List<PlaceBind>>>>()
    val allPlacesFreeLiveData: LiveData<Event<UIState<List<PlaceBind>>>> = _allPlacesFreeLiveData

    fun getAllPlacesFree() {

        subscriptions.add(
            Single.fromCallable { placeService.getPlacesAllByState(State.FREE) }
                .doOnSubscribe {
                    _allPlacesFreeLiveData.postValue(Event(OnLoading(true)))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        _allPlacesFreeLiveData.postValue(
                            Event(
                                OnSuccess( ListMapperImpl(PlaceToPlaceBind())
                                    .map(it))
                            )
                        )
                    },
                    onError = {
                        _allPlacesFreeLiveData.postValue(Event( OnError(it.message ?: OnError.ERROR )))
                    }
                )
        )
    }

    fun closeSubscriptions() {
        subscriptions.dispose()
    }
}