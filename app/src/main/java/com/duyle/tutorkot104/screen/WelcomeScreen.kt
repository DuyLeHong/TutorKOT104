package com.duyle.tutorkot104.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.duyle.tutorkot104.R
import com.duyle.tutorkot104.ROUTE_SCREEN_NAME
import kotlinx.coroutines.delay

//@Preview
@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column (
        Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")

        Text(text = "PH11324", style = MaterialTheme.typography.titleLarge)
    }

    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate(ROUTE_SCREEN_NAME.HOME.name) {
            popUpTo(ROUTE_SCREEN_NAME.WELCOME.name) { inclusive = true }
        }
    }
}