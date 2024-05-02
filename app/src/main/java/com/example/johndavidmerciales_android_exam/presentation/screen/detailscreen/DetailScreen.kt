package com.example.johndavidmerciales_android_exam.presentation.screen.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Details",
                        style = TextStyle(textAlign = TextAlign.Center)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.setEvent(DetailScreenContract.DetailEvent.OnPopBackClick)}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Content(
                viewModel.state,
                paddingValues)
        }
    )
}

@Composable
fun Content(
    state: DetailScreenContract.DetailState,
    paddingValues: PaddingValues
) {

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "${state.title}. ${state.firstName} ${state.lastName}",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.emailAddress,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = state.mobileNumber,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier
                .padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = state.birthday,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                text = state.age,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                text = state.gender,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                text = state.address,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            )
        }

    }
}
