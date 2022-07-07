package com.bashkir.sporttest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.sporttest.R
import com.bashkir.sporttest.data.SportState
import com.bashkir.sporttest.data.SportViewModel
import com.bashkir.sporttest.ui.Screen
import com.bashkir.sporttest.ui.components.GradientButton

@Composable
fun QuestScreen(navController: NavController, viewModel: SportViewModel) {
    Image(
        painterResource(id = R.drawable.background),
        null,
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (buttons, image) = createRefs()
        val enemyOptions by viewModel.collectAsState(SportState::enemyOptions)
        val questPlayer by viewModel.collectAsState(SportState::player)
        Image(
            painterResource(id = questPlayer!!.img),
            null,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .constrainAs(image) {
                    bottom.linkTo(buttons.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Inside
        )

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
                .constrainAs(buttons) {
                    bottom.linkTo(parent.bottom)
                }) {
            items(enemyOptions) { player ->
                GradientButton(text = player.name, Modifier.padding(vertical = 20.dp)) {
                    viewModel.answer(player)
                    navController.navigate(Screen.AnswerScreen.destination)
                }
            }
        }
    }
}