package com.vee.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.vee.musicapp.base.AppViewModelFactory
import com.vee.musicapp.data.DataSource
import com.vee.musicapp.data.repo.MovieRepoImpl
import com.vee.musicapp.firebase.SyncEventsWorker
import com.vee.musicapp.navigation.Navigation
import com.vee.musicapp.ui.theme.MusicAppTheme
import com.vee.musicapp.util.AppConstants
import com.vee.musicapp.viewmodel.MovieViewModel
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    val tag = this.javaClass.name
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieRepository: MovieRepoImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        syncServiceInit()
        viewModelSetup()
        setContent {
            MusicAppTheme {
                val terminationState = viewModel.serviceTerminateState.collectAsState()
                val showDialog by viewModel.showDialog
                Navigation(viewModel)
                if (terminationState.value.lock && !showDialog) {
                    AlertDialog(onDismissRequest = { viewModel.showDialog.value = false }, title = {
                        Text(
                            text = AppConstants.alert, color = Color.Yellow,
                            style = MaterialTheme.typography.titleMedium,

                            )
                    }, text = {
                        Text(
                            text = terminationState.value.message,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }, confirmButton = {
                        Button(onClick = {
                            viewModel.showDialog.value = false
                            finishAffinity()
                        }) {
                            Text(
                                "OK",
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    })
                }
            }
        }
    }

    private fun viewModelSetup() {
        movieRepository = MovieRepoImpl(DataSource())
        val factory = AppViewModelFactory(this.application, movieRepository)
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
    }

    private fun syncServiceInit() {
        val internetConstraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // Only run when the internet is available
            .build()

        val workRequest: WorkRequest = PeriodicWorkRequestBuilder<SyncEventsWorker>(1, TimeUnit.HOURS)
            .setInitialDelay(2, TimeUnit.MINUTES)  // Optional delay
            .setConstraints(internetConstraint)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

}


