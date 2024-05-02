package com.example.johndavidmerciales_android_exam.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.johndavidmerciales_android_exam.presentation.utils.NavEvent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

interface MyController {
    suspend fun sendUiEvent(event: NavEvent)
}

@ActivityRetainedScoped
class AppController @Inject constructor(
) : MyController {

    private val _uiEvent = Channel<NavEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    lateinit var navController: NavController

    override suspend fun sendUiEvent(event: NavEvent) {
        _uiEvent.send(event)
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AppController.CollectRoutes(
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when (event) {
                is NavEvent.PopBackStack -> navController.popBackStack()
                is NavEvent.Navigate -> navController.navigate(event.route)
            }
        }
    }
}