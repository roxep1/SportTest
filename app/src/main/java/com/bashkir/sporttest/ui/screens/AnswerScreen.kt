package com.bashkir.sporttest.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.sporttest.R
import com.bashkir.sporttest.data.SportState
import com.bashkir.sporttest.data.SportViewModel
import com.bashkir.sporttest.ui.Screen
import com.bashkir.sporttest.ui.components.GradientButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnswerScreen(navController: NavController, viewModel: SportViewModel) {
    val answer by viewModel.collectAsState(SportState::answer)
    val rivalry by viewModel.collectAsState(SportState::rivalry)
    val questPlayer by viewModel.collectAsState(SportState::player)

    Image(
        painterResource(id = R.drawable.background),
        null,
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    LazyColumn(Modifier.padding(vertical = 10.dp), horizontalAlignment = CenterHorizontally) {
        item {
            if (rivalry!!.players.toList().contains(answer))
                Text(
                    "ВЕРНО!",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            else {
                Text(
                    "НЕВЕРНО",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(Modifier.padding(5.dp))
                Text(
                    "Правильный ответ: ${
                        rivalry!!.players.toList().minus(questPlayer).first()!!.name
                    }"
                )
            }
        }
        item {
            Text(
                rivalry!!.history,
                textAlign = TextAlign.Justify,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
        stickyHeader {
            GradientButton(text = "OK") {
                if (viewModel.isEnough())
                    navController.navigate(Screen.LaunchScreen.destination)
                else {
                    navController.navigate(Screen.QuestScreen.destination)
                    viewModel.getQuestion()
                }
            }
        }
    }
}