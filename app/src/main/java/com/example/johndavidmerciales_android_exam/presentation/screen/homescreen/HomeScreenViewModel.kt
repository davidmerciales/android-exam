package com.example.johndavidmerciales_android_exam.presentation.screen.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.johndavidmerciales_android_exam.data.model.PersonEntity
import com.example.johndavidmerciales_android_exam.domain.model.request.GetPersonUseCaseRequest
import com.example.johndavidmerciales_android_exam.domain.usecase.GetLocalPersonsUseCase
import com.example.johndavidmerciales_android_exam.domain.usecase.GetRemotePersonUseCase
import com.example.johndavidmerciales_android_exam.domain.usecase.InsertPersonUseCase
import com.example.johndavidmerciales_android_exam.presentation.navigation.AppController
import com.example.johndavidmerciales_android_exam.presentation.utils.NavEvent
import com.example.johndavidmerciales_android_exam.presentation.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPersonRemoteUseCase: GetRemotePersonUseCase,
    private val insertPersonUseCase: InsertPersonUseCase,
    private val getLocalPersonsUseCase: GetLocalPersonsUseCase,
    appController: AppController
) : ViewModel() {

    val state: HomeScreenContract.HomeState = HomeScreenContract.MutableHomeState()
    private val _event = MutableSharedFlow<HomeScreenContract.HomeEvent>()
    private val event: SharedFlow<HomeScreenContract.HomeEvent> = _event.asSharedFlow()

    init {
        getRemotePersonList(false)
        getInitialLocalPersonList()

        viewModelScope.launch {

            event.collect { event ->
                when (event) {
                    HomeScreenContract.HomeEvent.OnLoadMore -> {
                        getRemotePersonList(true)
                        getInitialLocalPersonList()
                    }

                    is HomeScreenContract.HomeEvent.OnSwipeRefresh -> {

                    }

                    is HomeScreenContract.HomeEvent.OnPersonItemClicked-> {
                        appController.sendUiEvent(NavEvent.Navigate(Routes.DetailScreen + "?personId=${event.id}"))
                    }
                }
            }
        }
    }

    private fun getInitialLocalPersonList() = viewModelScope.launch{
       getLocalPersonsUseCase().collect{ personsRaw->
           state.persons = personsRaw
       }
    }

    private fun getRemotePersonList(isLoadMore: Boolean) = viewModelScope.launch {
        if (isLoadMore){
            state.isLoading = true
        }
        getPersonRemoteUseCase(
            params = GetPersonUseCaseRequest(
                page = if (isLoadMore) (state.page + 1).toString() else state.page.toString(),
                results = state.results.toString(),
                seed = state.seed
            ),
            onSuccess = { results ->
                insertPersonUseCase(
                    results.results.map { person ->
                        state.page = results.info.page

                        PersonEntity(
                            id = person.login.uuid,
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
                )
                state.isLoading = false
            },
            onFailure = {
                state.isLoading = false
            }
        )
    }


    fun setEvent(event: HomeScreenContract.HomeEvent) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }
}