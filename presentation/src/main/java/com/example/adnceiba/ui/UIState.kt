package com.example.adnceiba.ui

import com.example.adnceiba.R

sealed interface UIState<out T>
data class OnLoading(val loading: Boolean): UIState<Nothing>
data class OnSuccess<T> (val data: T): UIState<T>

data class OnError(val error: String): UIState<Nothing>{
    companion object {
        const val ERROR = R.string.title_error.toString()
    }
}