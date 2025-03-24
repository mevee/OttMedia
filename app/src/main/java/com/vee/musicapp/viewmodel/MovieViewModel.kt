package com.vee.musicapp.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.vee.musicapp.data.local.dao.LogsDao
import com.vee.musicapp.data.local.db.LogDatabase
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
    private val context: Application, private val repo: MovieRepoImpl
) : ViewModel() {

    companion object {
        private const val TAG = "MovieViewModel"
    }

    var showDialog = mutableStateOf(false)

    private val _uiState = MutableStateFlow<HomeState<List<Category>>>(HomeState.Loading)
    val uiState: StateFlow<HomeState<List<Category>>> = _uiState.asStateFlow()

    private val _serviceTerminateState = MutableStateFlow(DialogConfig(false, ""))
    val serviceTerminateState: StateFlow<DialogConfig> = _serviceTerminateState.asStateFlow()

    private lateinit var dao: LogsDao
    private lateinit var remoteConfigHelper: RemoteConfigHelper
    private lateinit var analyticsHelper: FirebaseAnalyticsHelper

    init {
        initializeHelpers()
        checkServiceTermination()
    }

    private fun initializeHelpers() {
        remoteConfigHelper = RemoteConfigHelper()
        dao = LogDatabase.getDatabase(context).logDao()
        analyticsHelper = FirebaseAnalyticsHelper(context, dao)
    }

    fun logData(event: LogEventType, data: Movie, railId: String) {
        val bundle = Bundle().apply {
            putString("movie", data.name)
            putString("railId", railId)
        }
        analyticsHelper.logEvent(event.eventName, bundle)
    }

    fun checkServiceTermination() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                remoteConfigHelper.fetchConfig()
                val dialogConfig = remoteConfigHelper.getDialogConfig()
                _serviceTerminateState.value = dialogConfig

                if (dialogConfig.lock) {
                    _uiState.value = HomeState.Error(message = dialogConfig.message)
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                _uiState.value =
                    HomeState.Error("Failed to check service termination: ${e.message}")
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

                if (!reloadAfterError) throw IllegalStateException("Json Malfunctioned")

                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _uiState.value = if (data.isNotEmpty()) {
                            FirebaseCrashlytics.getInstance().log("App Loaded Successfully")
                            HomeState.Success(data)
                        } else {
                            HomeState.Error("No Data available")
                        }
                    } ?: run {
                        _uiState.value = HomeState.Error("Response body is null")
                    }
                } else {
                    _uiState.value = HomeState.Error("API Failed with code: ${response.code()}")
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                _uiState.value = HomeState.Error("Unexpected error: ${e.message}")
            }
        }
    }

    private fun loadNewRail(lastIndex: Int) {

    }
}
