package com.bashkir.sporttest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bashkir.sporttest.data.SportViewModel
import com.bashkir.sporttest.ui.Screen.LaunchScreen
import com.bashkir.sporttest.ui.Screen.QuestScreen
import com.bashkir.sporttest.ui.screens.AnswerScreen
import com.bashkir.sporttest.ui.screens.LaunchScreen
import com.bashkir.sporttest.ui.screens.QuestScreen

sealed class Screen(val destination: String) {
    object LaunchScreen : Screen("launch_screen")
    object QuestScreen : Screen("quest_screen")
    object AnswerScreen : Screen("answer_screen")
}

@Composable
fun MainNavHost(navHostController: NavHostController, viewModel: SportViewModel) = NavHost(
    navController = navHostController,
    startDestination = LaunchScreen.destination
) {
    composable(LaunchScreen.destination) {
        LaunchScreen(navHostController, viewModel)
    }

    composable(QuestScreen.destination) {
        QuestScreen(navHostController, viewModel)
    }

    composable(Screen.AnswerScreen.destination) {
        AnswerScreen(navHostController, viewModel)
    }
}