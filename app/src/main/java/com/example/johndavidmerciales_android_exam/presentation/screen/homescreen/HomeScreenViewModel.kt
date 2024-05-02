package com.example.johndavidmerciales_android_exam.presentation.screen.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.johndavidmerciales_android_exam.domain.model.Person
import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.usecase.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase
) : ViewModel() {

    val state: HomeScreenContract.HomeState = HomeScreenContract.MutableHomeState()
    private val _event = MutableSharedFlow<HomeScreenContract.HomeEvent>()
    private val event: SharedFlow<HomeScreenContract.HomeEvent> = _event.asSharedFlow()

    init {
        getInitialPersonList()

        viewModelScope.launch {

            event.collect { event ->
                when (event) {
                    is HomeScreenContract.HomeEvent.OnLoadMore -> {

                    }

                    is HomeScreenContract.HomeEvent.OnSwipeRefresh -> {

                    }
                }
            }
        }
    }

    private fun getInitialPersonList() = viewModelScope.launch {
        getPersonUseCase(
            params = GetPersonUseCaseRequest(
                page = "1",
                results = "1",
                seed = "1"
            ),
            onSuccess = { results ->
                state.persons = results.results.map { person ->
                    Person(
                        title = person.name.title,
                        firstName = person.name.first,
                        lastName = person.name.last,
                        gender = person.gender,
                        birthday = person.dob.date,
                        age = person.dob.age,
                        emailAddress = person.email,
                        mobileNumber = person.cell,
                        address = "${person.location.state}, ${person.location.city}"
                    )
                }
            },
            onFailure = {

            }
        )
    }


    fun setEvent(event: HomeScreenContract.HomeEvent) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }
}