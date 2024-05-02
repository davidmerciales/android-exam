package com.example.johndavidmerciales_android_exam.presentation.utils

sealed class NavEvent {
    data object PopBackStack : NavEvent()
    data class Navigate(val route: String) : NavEvent()
}