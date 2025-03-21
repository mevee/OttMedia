package com.vee.musicapp.modules.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.tv.material3.Text
import com.vee.musicapp.navigation.Routes
import com.vee.musicapp.ui.theme.Dimens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,message:String) {
    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate(Routes.Home.routeNane) {
            popUpTo(Routes.Splash.routeNane) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(Dimens.dp16))
            CircularProgressIndicator(color = Color.White)
        }
    }
}