package com.vee.musicapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vee.musicapp.data.repo.MovieRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MovieViewModelTest {
    private val  testDispatcher = StandardTestDispatcher()
    @Mock
    lateinit var repoImpl: MovieRepoImpl
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getHomeDataWithEmptyList() = runTest{
        Mockito.`when` ((repoImpl.getHomePageData())).thenReturn(Response.success(emptyList()))
        val vm = MovieViewModel(repoImpl)
        testDispatcher.scheduler.advanceUntilIdle()
        val data = vm.homePageLiveData.value
        assertEquals(true, data.isNullOrEmpty())
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

}