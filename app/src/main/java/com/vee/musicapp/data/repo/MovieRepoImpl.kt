package com.vee.musicapp.data.repo

import com.vee.musicapp.data.DataSource
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import kotlinx.coroutines.delay
import retrofit2.Response

class MovieRepoImpl(private val dataSource: DataSource) : MovieRepo {


    override suspend fun getHomeData(): Response<List<Movie>> {
        delay(100)
        val data = dataSource.getMovieListVertical().toList()
        val response = Response.success(data)
        return response
    }

    override suspend fun getHomePageData(): Response<List<Category>> {
        delay(2000)
        val data = dataSource.getHomePageData()
        val response = Response.success(data)
        return response
    }

    @Throws
    override suspend fun loadNextRail(index: Int): Response<List<Category>> {
        delay(2000)
        val mainList = dataSource.getHomePageData()
        if (index < mainList.size) {
            val nextList = mainList.takeLast(index)
            val response = Response.success(nextList)
            return response
        } else {
            throw Exception("Index out of bound")
        }
    }


    @Throws
    override suspend fun loadNextRailShows(currentIndex: Int): Response<List<Movie>> {
        delay(1000)
        val mainList = dataSource.getMovieListVertical()
        val nextList = mainList.takeLast(currentIndex)
        if (nextList.isEmpty()) {
            throw Exception("No data rail show available")
        }
        val response = Response.success(nextList)
        return response

    }
}