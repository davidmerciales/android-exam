package com.example.johndavidmerciales_android_exam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.johndavidmerciales_android_exam.presentation.screen.homescreen.HomeScreen
import com.example.johndavidmerciales_android_exam.presentation.ui.theme.JohnDavidMercialesandroidexamTheme
import com.example.johndavidmerciales_android_exam.presentation.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JohnDavidMercialesandroidexamTheme {
                Log.d("onCreate:", "sfasf")
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.HomeScreen
                ){

                    composable(Routes.HomeScreen){
                        HomeScreen()
                    }

                }
            }
        }
    }
}