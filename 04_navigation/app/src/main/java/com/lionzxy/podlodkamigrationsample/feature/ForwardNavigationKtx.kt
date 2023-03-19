package com.lionzxy.podlodkamigrationsample.feature

import android.net.Uri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lionzxy.podlodkamigrationsample.utils.GlobalNavController

fun NavGraphBuilder.forwardFeature(
    navController: NavController,
    defaultContainer: String
) {
    composable(
        route = "forward?number={number}&container={container}",
        arguments = listOf(
            navArgument("number") {
                type = NavType.IntType
                defaultValue = 0
            },
            navArgument("container") {
                type = NavType.StringType
                defaultValue = defaultContainer
            }
        )
    ) { entry ->
        val number = entry.arguments?.getInt("number")!!
        val container = entry.arguments?.getString("container")!!
        val viewModel: ForwardViewModel = viewModel(
            factory = ForwardViewModelFactory(
                containerName = container,
                number = number,
                navController = navController
            )
        )
        val globalNavController = GlobalNavController.current
        ComposableForward(
            forwardState = viewModel.forwardState,
            onForward = viewModel::onForward,
            onBack = viewModel::onBack,
            onFullscreen = { viewModel.onForwardFullScreen(globalNavController) }
        )
    }
}

fun NavController.toForward(number: Int, container: String? = null) {
    val route = if (container == null) {
        "forward?number=${Uri.encode(number.toString())}"
    } else "forward?container=${Uri.encode(container)}&number=${Uri.encode(number.toString())}"
    navigate(route)
}