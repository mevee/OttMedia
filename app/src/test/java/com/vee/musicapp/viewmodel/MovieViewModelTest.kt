package com.vee.musicapp.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.repo.MovieRepoImpl
import com.vee.musicapp.firebase.FirebaseAnalyticsHelper
import com.vee.musicapp.firebase.RemoteConfigHelper
import com.vee.musicapp.modules.home.HomeState
import com.vee.musicapp.util.isInternetAvailable
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
    @Mock
    private lateinit var context: Application
    @Mock
    private lateinit var repo: MovieRepoImpl
    @Mock
    private lateinit var remoteConfigHelper: RemoteConfigHelper
    @Mock
    private lateinit var analyticsHelper: FirebaseAnalyticsHelper

    private lateinit var viewModel: MovieViewModel

    private val  testDispatcher = StandardTestDispatcher()
    @Mock
    lateinit var repoImpl: MovieRepoImpl

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() // Ensures LiveData updates synchronously


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun `loadHomeData should set Loading state initially`() = runTest {
        // Given
        Mockito.`when`(repo.getHomePageData()).thenReturn(Response.success(emptyList()))
        // When
        viewModel.loadHomeData()
        // Then
        assert(viewModel.uiState.value is HomeState.Loading)
    }

    @Test
    fun `loadHomeData should return Success when API call is successful`() = runTest {
        // Given
        val fakeData = listOf(Category(name = "Action", type = "H", movies = emptyList()))
        Mockito.`when`(isInternetAvailable(context)).thenReturn(true)
        Mockito.`when`(repo.getHomePageData()).thenReturn(Response.success(fakeData))
        // When
        viewModel.loadHomeData()
        // Then
        assert(viewModel.uiState.value is HomeState.Success)
        assert((viewModel.uiState.value as HomeState.Success).data == fakeData)
    }

    @Test
    fun `loadHomeData should return Error when internet is not available`() = runTest {
        // Given
        val fakeData = listOf(Category(name = "Action", type = "H", movies = emptyList()))
        Mockito.`when`(isInternetAvailable(context)).thenReturn(false)
        Mockito.`when`(repo.getHomePageData()).thenReturn(Response.success(fakeData))
        // When
        viewModel.loadHomeData()
        // Then
        assert(viewModel.uiState.value is HomeState.NetworkError)
     }

    @Test
    fun `getHomeData should return Error when Api call success BUt data in list is Empty`() = runTest{
        Mockito.`when`(isInternetAvailable(context)).thenReturn(true)
        Mockito.`when` ((repoImpl.getHomePageData())).thenReturn(Response.success(emptyList()))

        viewModel.loadHomeData()
        assert(viewModel.uiState.value is HomeState.Success)
        assert((viewModel.uiState.value as HomeState.Success).data.isEmpty())

    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }
}