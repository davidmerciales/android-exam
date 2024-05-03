package com.example.johndavidmerciales_android_exam.presentation.screen.homescreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

private const val buffer = 1
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
){

    val listState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            homeScreenViewModel.setEvent(HomeScreenContract.HomeEvent.OnLoadMore)}
    }
    
    LazyColumn(
        modifier = Modifier
        .fillMaxWidth(),
        state = listState){
        item {
            Text(text = "Persons:")
        }
        items(homeScreenViewModel.state.persons){ person->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable {
                    homeScreenViewModel.setEvent(HomeScreenContract.HomeEvent.OnPersonItemClicked(person.id))
                }) {
                Text(
                    modifier = Modifier
                        .padding(15.dp),
                    text = person.firstName)
            }
        }
        if (homeScreenViewModel.state.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}