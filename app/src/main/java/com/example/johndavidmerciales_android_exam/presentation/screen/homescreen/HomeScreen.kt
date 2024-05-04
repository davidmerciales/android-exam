package com.example.johndavidmerciales_android_exam.presentation.screen.homescreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState

private const val buffer = 1
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenContract.HomeState,
    onEvent: (HomeScreenContract.HomeEvent)-> Unit
){

    val listState = rememberLazyListState()
    val refreshState = rememberPullRefreshState(refreshing = state.isRefreshing, onRefresh = {
        onEvent(HomeScreenContract.HomeEvent.OnSwipeRefresh) })

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            onEvent(HomeScreenContract.HomeEvent.OnLoadMore)}
    }
    Box{
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .pullRefresh(refreshState),
            state = listState){
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }

            items(state.persons){ person->

                PersonItem(
                    name = "${person.firstName} ${person.lastName}",
                    profileImg = person.profileImg,
                    person.address){
                    onEvent(
                        HomeScreenContract.HomeEvent.OnPersonItemClicked(
                            person.id
                        )
                    )
                }
            }
            if (state.isLoading) {
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

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = state.isRefreshing,
            state = refreshState)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PersonItem(
    name: String,
    profileImg: String,
    address: String,
    onItemClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            onItemClick()
        },
        ) {
        Column(modifier = Modifier
            .padding(horizontal = 10.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                GlideImage(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = 5.dp),
                    model = profileImg,
                    contentDescription = "profile")

                Column {

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 15.dp),
                        text = name,
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 15.dp),
                        text = address,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.Gray
                        ))
                }

            }
            HorizontalDivider(modifier = Modifier.padding(top = 5.dp), thickness = 1.dp)
        }
    }
}
