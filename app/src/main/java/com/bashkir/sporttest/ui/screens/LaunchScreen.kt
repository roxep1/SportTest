package com.bashkir.sporttest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bashkir.sporttest.R
import com.bashkir.sporttest.data.SportViewModel
import com.bashkir.sporttest.ui.Screen
import com.bashkir.sporttest.ui.components.GradientButton
import com.bashkir.sporttest.ui.theme.MainGradient

@Composable
fun LaunchScreen(navController: NavController, viewModel: SportViewModel) = Box {
    Image(
        painterResource(id = R.drawable.background),
        null,
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Image(
        painterResource(id = R.drawable.man),
        null,
        Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 10.dp),
        contentScale = ContentScale.Crop
    )
    Image(
        painterResource(id = R.drawable.guess_oppenents_text),
        null,
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .align(Alignment.Center),
        contentScale = ContentScale.Crop
    )
    GradientButton(
        text = "PLAY",
        modifier = Modifier
            .padding(vertical = 60.dp)
            .align(Alignment.BottomCenter)
    ) {
        viewModel.startTest()
        navController.navigate(Screen.QuestScreen.destination)
    }
}