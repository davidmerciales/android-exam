package com.example.johndavidmerciales_android_exam.presentation.screen.detailscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.model.Person
import kotlinx.coroutines.flow.Flow

class DetailScreenContract {

    sealed interface DetailEvent {
        data object OnPopBackClick: DetailEvent
    }

    interface DetailState {
        var persons: List<PersonEntity>
        var title: String
        var firstName: String
        var lastName: String
        var gender: String
        var birthday: String
        var age: String
        var emailAddress: String
        var mobileNumber: String
        var address: String
        var profileImg: String
    }

    class MutableDetailState: DetailState {
        override var persons: List<PersonEntity> by mutableStateOf(emptyList())
        override var title: String by mutableStateOf("")
        override var firstName: String by mutableStateOf("")
        override var lastName: String by mutableStateOf("")
        override var gender: String by mutableStateOf("")
        override var birthday: String by mutableStateOf("")
        override var age: String by mutableStateOf("")
        override var emailAddress: String by mutableStateOf("")
        override var mobileNumber: String by mutableStateOf("")
        override var address: String by mutableStateOf("")
        override var profileImg: String by mutableStateOf("")
    }
}