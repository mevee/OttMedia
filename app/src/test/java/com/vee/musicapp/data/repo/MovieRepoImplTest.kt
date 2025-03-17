package com.vee.musicapp.data.repo

import coil3.network.NetworkRequest
import com.vee.musicapp.data.AppData
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody

import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MovieRepoImplTest {
    @Mock
    lateinit var dataSrc: AppData

    @Before
    fun setUp() {
        println("Start of test")
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        println("End of test")
    }

    @Test
    fun getHomeRailEmptyList() = runTest {
        Mockito.`when`((dataSrc.getHomePageData())).thenReturn(emptyList())
        val repo = MovieRepoImpl(dataSrc)
        val data = repo.getHomePageData()
        assertEquals(true, data.isSuccessful)
        assertEquals(false, data.body()?.isNotEmpty())
    }

    @Test
    fun getHomeRailWithDataList() = runTest {
        Mockito.`when`((dataSrc.getHomePageData())).thenReturn(
            arrayListOf(
                Category(
                    type = "V", name = "Test1",
                    movies = arrayListOf(
                        Movie(name = "Text Movie",
                             subTitle = "sub title 1",
                            url = "https::www.google.com",
                            )
                    ),
                )
            )
        )
        val repo = MovieRepoImpl(dataSrc)
        val data = repo.getHomePageData()
        assertEquals(true, data.isSuccessful)
        assertEquals(1, data.body()?.size)
        assertEquals("Test1", data.body()!![0].name)
        assertEquals("V", data.body()!![0].type)

    }

    @Test
    fun getNextRailWithList() = runTest {
        Mockito.`when`((dataSrc.getHomePageData())).thenReturn(arrayListOf(  Category(
            type = "V", name = "Test1",
            movies = arrayListOf(
                Movie(name = "Text Movie",
                    subTitle = "sub title 1",
                    url = "https::www.google.com",
                )
            ),
        )))
        val repo = MovieRepoImpl(dataSrc)
        try {
        val data = repo.loadNextRail(0)
            assertEquals(true, data.isSuccessful)
            assertEquals(0, data.body()?.size)
        }catch (e:Exception){
             assertEquals("Index out of bound", e.message)
        }
    }

    @Test
    fun getNextRailWithEmptyList() = runTest {
        Mockito.`when`((dataSrc.getHomePageData())).thenReturn(emptyList())
        val repo = MovieRepoImpl(dataSrc)
        try {
            val data = repo.loadNextRail(1)
            assertEquals(true, data.isSuccessful)
            assertEquals(true, data.body().isNullOrEmpty())
        }catch (e:Exception){
            assertEquals("Index out of bound", e.message)
        }
    }

}