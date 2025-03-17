package com.vee.musicapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.data.repo.MovieRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(
    mRepo: MovieRepoImpl
) : ViewModel() {
    val tag = "MovieViewModel"
    private val repo: MovieRepoImpl = mRepo
    private val _pageData = MutableLiveData<Category>()
    val viewPageData: LiveData<Category> = _pageData
    private val _homePageData = MutableLiveData<List<Category>>()
    val homePageLiveData: LiveData<List<Category>> = _homePageData

    //    val movieList = MutableLiveData<List<Movie>>()
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

    private fun loadHomeData() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getHomePageData()
            isLoading.postValue(false)
            if (response.isSuccessful) {
                val data = response.body()
                if (!data.isNullOrEmpty()) {
                    _homePageData.postValue(data!!)
                    //extracting the first category from the list for banner
                    if (data.isNotEmpty()) {
                        val movie = data.first()
                        _pageData.postValue(movie)
                    }
                }
            } else {

            }
        }
    }
}
