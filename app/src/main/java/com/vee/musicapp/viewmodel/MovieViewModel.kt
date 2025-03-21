package com.vee.musicapp.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.DialogConfig
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.data.repo.MovieRepoImpl
import com.vee.musicapp.firebase.FirebaseAnalyticsHelper
import com.vee.musicapp.firebase.RemoteConfigHelper
import com.vee.musicapp.modules.home.HomeState
import com.vee.musicapp.util.LogEventType
import com.vee.musicapp.util.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MovieViewModel(
    private val context: Application,
    mRepo: MovieRepoImpl,
) : ViewModel() {
    val tag = "MovieViewModel"
    private val repo: MovieRepoImpl = mRepo
    var showDialog = mutableStateOf(false)

    private val _uiState = MutableStateFlow<HomeState<List<Category>>>(HomeState.Loading)
    val uiState: StateFlow<HomeState<List<Category>>> = _uiState.asStateFlow()
    private val remoteConfigHelper = RemoteConfigHelper()
    private val analyticsHelper = FirebaseAnalyticsHelper(context)
    private val _serviceTerminateState = MutableStateFlow<DialogConfig>(DialogConfig(false, ""))
    val serviceTerminateState: StateFlow<DialogConfig> = _serviceTerminateState.asStateFlow()

    init {
        checkServiceTermination()
    }

    fun logData(event: LogEventType, data: Movie, railId: String) {
        when (event) {
            LogEventType.MovieClicked -> {
                analyticsHelper.logEvent(LogEventType.MovieClicked.eventName, Bundle().apply {
                    putString("movie", data.name)
                    putString("railId", data.name)
                })
            }

            LogEventType.MovieCardVisited -> {
                analyticsHelper.logEvent(LogEventType.MovieCardVisited.eventName, Bundle().apply {
                    putString("movie", data.name)
                    putString("railId", railId)
                })
            }
        }

    }

    fun checkServiceTermination() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteConfigHelper.fetchConfig()
            val dialogConfig = remoteConfigHelper.getDialogConfig()
            _serviceTerminateState.value = dialogConfig
            if (dialogConfig.lock) {
                _uiState.value = HomeState.Error(message = dialogConfig.message)
            }
        }
    }

    fun loadHomeData(reloadAfterError: Boolean = true) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = HomeState.Loading
            delay(1000)
            if (!isInternetAvailable(context)) {
                _uiState.value = HomeState.NetworkError
                return@launch
            }

            try {
                val response = repo.getHomePageData()

                if (!reloadAfterError) throw Exception("Json Malfunctioned")

                if (response.isSuccessful) {
                    val data = response.body()

                    if (!data.isNullOrEmpty()) {
                        _uiState.value = HomeState.Success(data = data)
                    } else {
                        _uiState.value = HomeState.Error(message = "No Data available")
                    }
                } else {
                    _uiState.value =
                        HomeState.Error(message = "API Failed with code: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = HomeState.Error(message = "Unexpected error: ${e.message}")
            }
        }
    }

    private fun loadNewRail(lastIndex: Int) {

    }
}
