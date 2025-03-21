package com.vee.musicapp.data.repo

import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import retrofit2.Response

interface MovieRepo {

    suspend fun getHomeData(): Response<List<Movie>>

    suspend fun getHomePageData(): Response<List<Category>>

    @Throws
    suspend fun loadNextRail(index: Int): Response<List<Category>>

    @Throws
    suspend fun loadNextRailShows(currentIndex: Int): Response<List<Movie>>
}