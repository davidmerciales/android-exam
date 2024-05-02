package com.example.johndavidmerciales_android_exam.presentation.screen.detailscreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.repository.PersonEntityRepository
import com.example.johndavidmerciales_android_exam.domain.usecase.GetLocalPersonsUseCase
import com.example.johndavidmerciales_android_exam.domain.usecase.GetRemotePersonUseCase
import com.example.johndavidmerciales_android_exam.domain.usecase.InsertPersonUseCase
import com.example.johndavidmerciales_android_exam.presentation.navigation.AppController
import com.example.johndavidmerciales_android_exam.presentation.navigation.MyController
import com.example.johndavidmerciales_android_exam.presentation.utils.NavEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val personEntityRepository: PersonEntityRepository,
    savedStateHandle: SavedStateHandle,
    appController: AppController
) : ViewModel(), MyController by appController  {

    val state: DetailScreenContract.DetailState = DetailScreenContract.MutableDetailState()
    private val _event = MutableSharedFlow<DetailScreenContract.DetailEvent>()
    private val event: SharedFlow<DetailScreenContract.DetailEvent> = _event.asSharedFlow()

    init {
        val personId = savedStateHandle.get<String>("personId")
        if (personId != "") {
            viewModelScope.launch {
                personEntityRepository.getPersonById(personId).let { person ->
                    state.title = person.title
                    state.firstName = person.firstName
                    state.lastName = person.lastName
                    state.gender = person.gender
                    state.birthday = person.birthday
                    state.age = person.age.toString()
                    state.emailAddress = person.emailAddress
                    state.mobileNumber = person.mobileNumber
                    state.address = person.address
                }
            }
        }
        viewModelScope.launch {

            event.collect { event ->
                when (event) {
                    DetailScreenContract.DetailEvent.OnPopBackClick -> {
                        appController.sendUiEvent(NavEvent.PopBackStack)
                    }
                }
            }
        }
    }

    fun setEvent(event: DetailScreenContract.DetailEvent) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }
}