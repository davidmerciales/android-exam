package com.example.johndavidmerciales_android_exam.presentation.screen.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.johndavidmerciales_android_exam.domain.model.Person

class HomeScreenContract {

    sealed interface HomeEvent {
        data class OnLoadMore(var isLoadMore: Boolean) : HomeEvent
        data class OnSwipeRefresh(var isSwipeRefresh: Boolean) : HomeEvent
    }

    interface HomeState {
        var persons: List<Person>
    }

    class MutableHomeState: HomeState {
        override var persons: List<Person> by mutableStateOf(emptyList())
    }
}