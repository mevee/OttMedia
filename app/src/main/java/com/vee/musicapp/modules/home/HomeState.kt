package com.vee.musicapp.modules.home

sealed  class HomeState<out T> {
    data object Loading : HomeState<Nothing>()
    data class Success<T>(val data: T) : HomeState<T>()
    data class Error(val message: String) : HomeState<Nothing>()
    data object NetworkError : HomeState<Nothing>()
}