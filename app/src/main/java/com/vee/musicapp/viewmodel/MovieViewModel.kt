package com.vee.musicapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.DialogConfig
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.data.repo.MovieRepoImpl
import com.vee.musicapp.firebase.RemoteConfigHelper
import com.vee.musicapp.modules.home.HomeState
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
    private val _homePageData = MutableLiveData<List<Category>>()
    val homePageLiveData: LiveData<List<Category>> = _homePageData
    private val _uiState = MutableStateFlow<HomeState<List<Category>>>(HomeState.Loading)
    val uiState: StateFlow<HomeState<List<Category>>> = _uiState.asStateFlow()
    val currentShow = MutableLiveData<Movie>()
    val remoteConfigHelper = RemoteConfigHelper()

    private val _serviceTerminateState = MutableStateFlow<DialogConfig>(DialogConfig(false, ""))
    val serviceTerminateState: StateFlow<DialogConfig> = _serviceTerminateState.asStateFlow()

    init {
        loadHomeData()
        checkServiceTermination()
    }

    fun checkServiceTermination() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteConfigHelper.fetchConfig()
            val dialogConfig = remoteConfigHelper.getDialogConfig()
            _serviceTerminateState.value = dialogConfig

        }

    }

    fun loadHomeData(reloadAfterError: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = HomeState.Loading
            delay(2000) // Simulate network delay
            if (isInternetAvailable(context)) {
                try {
                    val response = repo.getHomePageData()
                    if (!reloadAfterError) throw Exception("Json Malfunctioned")
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (!data.isNullOrEmpty()) {
                            _uiState.value = HomeState.Success(data = data)
//                            _homePageData.postValue(data!!)
//                            if (data.isNotEmpty()) {
//                                val movie = data.first()
//                                _pageData.postValue(movie)
//                            }else{
//                            }
                        } else {
                            _uiState.value = HomeState.Error(message = "No Data available")

                        }
                    } else {
                        _uiState.value = HomeState.Error(message = "")
                    }
                } catch (e: Exception) {
                    _uiState.value = HomeState.Error(message = e.message.toString())
                }
            } else {
                _uiState.value = HomeState.NetworkError;
            }
        }
    }

    private fun loadNewRail(lastIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.loadNextRail(lastIndex)
            if (response.isSuccessful) {
                val data = response.body()
                if (!data.isNullOrEmpty()) {
                    val updatedList = _homePageData.value.orEmpty() + data
                    val oldData = _homePageData.value
                    if (oldData != null) {
                        _homePageData.postValue(updatedList)
                    }
                }
            }
        }
    }
}
