package com.vee.musicapp.data.repo

import com.vee.musicapp.data.AppData
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import kotlinx.coroutines.delay

class MovieRepository {

    suspend fun getHomeData():List<Movie>{
      delay(100)
      return AppData.getMovieListVertical().toList()
    }
    suspend fun getHomePageData():List<Category>{
        delay(2000)
        return AppData.getHomePageData()
    }
}