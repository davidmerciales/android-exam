package com.example.johndavidmerciales_android_exam.presentation.screen.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailScreen(
    state: DetailScreenContract.DetailState,
    onEvent: (DetailScreenContract.DetailEvent) -> Unit
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Details",
                        style = TextStyle(
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(DetailScreenContract.DetailEvent.OnPopBackClick)
                        }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Content(
                state,
                paddingValues
            )
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .size(150.dp)
                    .clip(CircleShape),
                model = state.profileImg,
                contentDescription = "profile"
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "${state.title}. ${state.firstName} ${state.lastName}",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    modifier = Modifier
                        .size(18.dp),
                    imageVector = if (state.gender == "male") Icons.Default.Male else Icons.Default.Female,
                    tint = if (state.gender == "male") Color.Blue else Color(0xFFe860a6),
                    contentDescription = "verified"
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .padding(start = 3.dp),
            ) {

                Text(
                    text = state.birthday,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = state.age,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = state.emailAddress,
                modifier = Modifier
                    .padding(start = 3.dp),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = state.mobileNumber,
                modifier = Modifier
                    .padding(start = 3.dp),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = state.address,
                modifier = Modifier
                    .padding(start = 3.dp),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "About me",
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ultricies pretium justo id feugiat. In hac habitasse platea dictumst. Donec quis velit nisi. Mauris ultrices vel sapien et bibendum. Suspendisse quis erat velit. Nulla semper ac ante eget efficitur. Nulla rutrum mauris vitae ex pellentesque blandit.\n" +
                    "\n" +
                    "Donec non porttitor felis, id ultrices tortor. Duis efficitur nisl diam, eu interdum purus pellentesque sit amet. Donec mollis augue rhoncus, feugiat nisi non, tincidunt enim. Proin aliquet commodo dolor at condimentum. Nunc nec ipsum eros. In vitae ex elit. Suspendisse dapibus risus pellentesque, elementum justo ac, accumsan diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec a massa quis sapien iaculis laoreet sed ut sapien.\n" +
                    "\n",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            )
        )

    }
}
