package com.example.johndavidmerciales_android_exam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.johndavidmerciales_android_exam.presentation.navigation.AppController
import com.example.johndavidmerciales_android_exam.presentation.navigation.CollectRoutes
import com.example.johndavidmerciales_android_exam.presentation.screen.detailscreen.DetailScreen
import com.example.johndavidmerciales_android_exam.presentation.screen.homescreen.HomeScreen
import com.example.johndavidmerciales_android_exam.presentation.ui.theme.JohnDavidMercialesandroidexamTheme
import com.example.johndavidmerciales_android_exam.presentation.utils.Routes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appController: AppController
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JohnDavidMercialesandroidexamTheme {
                val navController = rememberNavController()
                appController.navController = navController
                appController.CollectRoutes()

                NavHost(
                    navController = navController,
                    startDestination = Routes.HomeScreen
                ){

                    composable(Routes.HomeScreen){
                        HomeScreen()
                    }

                    composable(Routes.DetailScreen  + "?personId={personId}",
                        arguments = listOf(
                            navArgument(name = "personId") {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )){
                        DetailScreen()
                    }
                }
            }
        }
    }
}