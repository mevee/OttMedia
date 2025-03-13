package com.vee.musicapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.data.repo.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel :
    ViewModel() {
    val tag = "MovieViewModel"
    private val repo: MovieRepository = MovieRepository()
    private val _pageData = MutableLiveData<Category>()
    val viewPageData: LiveData<Category> = _pageData
    private val _homePageData = MutableLiveData<List<Category>>()
    val homePageLiveData: LiveData<List<Category>> = _homePageData
    val movieList = MutableLiveData<List<Movie>>()
    val isLoading = MutableLiveData<Boolean>(false)
    val currentShow = MutableLiveData<Movie>()

    private val _focusedIndex = MutableLiveData("")  // Track focused item index
    val focusedIndex: LiveData<String> = _focusedIndex
    fun updateFocus(id: String) {
        _focusedIndex.value = id
        Log.d(tag, "updateFocus")
    }

    init {
        loadHomeData()
    }

    fun loadHomeData() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.getHomeData()
            val homeData = repo.getHomePageData()
            withContext(Dispatchers.Main) {
                movieList.postValue(data)
                _homePageData.postValue(homeData)
                if (homeData.isNotEmpty()) {
                    _pageData.postValue(homeData.first())
                }
                isLoading.postValue(false)
            }
        }
    }
}