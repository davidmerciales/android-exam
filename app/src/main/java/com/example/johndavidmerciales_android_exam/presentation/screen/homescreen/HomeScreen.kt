package com.example.johndavidmerciales_android_exam.presentation.screen.homescreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
){
    
    LazyColumn{
        items(homeScreenViewModel.state.persons){ person->
            Text(text = person.firstName)
        }
    }
}