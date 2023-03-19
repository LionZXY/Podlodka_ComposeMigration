package com.lionzxy.podlodkamigrationsample.feature

import android.net.Uri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel

fun NavGraphBuilder.forwardFeature(
    navController: NavController,
    container: String
) {
    composable(
        route = "forward?number={number}",
        arguments = listOf(
            navArgument("number") {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    ) { entry ->
        val number = entry.arguments?.getInt("number")!!
        val viewModel: ForwardViewModel = viewModel(
            factory = ForwardViewModelFactory(
                containerName = container,
                number = number,
                navController = navController
            )
        )
        ComposableForward(
            forwardState = viewModel.forwardState,
            onForward = viewModel::onForward,
            onBack = viewModel::onBack,
            onFullscreen = viewModel::onForwardFullScreen
        )
    }
}

fun NavController.toForward(container: String, number: Int) {
    navigate("forward?container=${Uri.encode(container)}&number=${Uri.encode(number.toString())}")
}