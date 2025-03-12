package com.vee.musicapp.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.vee.musicapp.data.repo.MovieRepository

class AppViewModelFactory (private val context: Application, private val repository: MovieRepository):
    ViewModelProvider.Factory {

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return   ViewModelProvider(context, repository) as T
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModelFactory(context, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}