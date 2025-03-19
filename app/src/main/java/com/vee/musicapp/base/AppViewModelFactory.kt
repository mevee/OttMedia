package com.vee.musicapp.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.vee.musicapp.data.repo.MovieRepoImpl
import com.vee.musicapp.viewmodel.MovieViewModel

class AppViewModelFactory(
    private val context: Application, val repository: MovieRepoImpl
) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            val factory = MovieViewModel(repository)
//            return factory as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(context,repository) as T
    }
}