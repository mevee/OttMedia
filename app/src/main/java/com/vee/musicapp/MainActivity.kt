package com.vee.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.vee.musicapp.base.AppViewModelFactory
import com.vee.musicapp.data.DataSource
import com.vee.musicapp.data.repo.MovieRepoImpl
import com.vee.musicapp.modules.home.HomeScreen
import com.vee.musicapp.nivigation.Navigation
import com.vee.musicapp.ui.theme.MusicAppTheme
import com.vee.musicapp.util.AppConstants
import com.vee.musicapp.viewmodel.MovieViewModel


class MainActivity : ComponentActivity() {
    val tag = this.javaClass.name
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieRepository: MovieRepoImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModelSetup()
        setContent {
            MusicAppTheme {
                val terminationState = viewModel.serviceTerminateState.collectAsState()
                val showDialog by viewModel.showDialog
                Navigation(viewModel)
                if (terminationState.value.lock && !showDialog) {
                    AlertDialog(
                        onDismissRequest = { viewModel.showDialog.value = false },
                        title = { Text(text = AppConstants.aler) },
                        text = { Text(text = terminationState.value.message) },
                        confirmButton = {
                            Button(onClick = { viewModel.showDialog.value = false }) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
        }
    }

    private fun viewModelSetup() {
        movieRepository = MovieRepoImpl(DataSource())
        val factory = AppViewModelFactory(this.application, movieRepository)
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }
}


