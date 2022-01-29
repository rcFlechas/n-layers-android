package com.example.adnceiba.ui

import com.example.adnceiba.R

sealed class UIState<out T>  {
    class OnLoading(val loading: Boolean): UIState<Nothing>()
    class OnSuccess<T> (val data: T): UIState<T>()
    class OnError(val error: String): UIState<Nothing>()

    companion object {
        const val ERROR = R.string.title_error.toString()
    }
}