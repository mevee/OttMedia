package com.vee.musicapp.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.vee.musicapp.data.repo.MovieRepository

open class BaseViewModel(val application: Application, private val repo: MovieRepository) : ViewModel()