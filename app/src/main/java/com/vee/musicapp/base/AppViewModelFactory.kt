package com.vee.musicapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.vee.musicapp.data.repo.MovieRepoImpl
import com.vee.musicapp.viewmodel.MovieViewModel

class AppViewModelFactory ( val repository: MovieRepoImpl):
    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            val factory = MovieViewModel(repository)
//            return factory as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}