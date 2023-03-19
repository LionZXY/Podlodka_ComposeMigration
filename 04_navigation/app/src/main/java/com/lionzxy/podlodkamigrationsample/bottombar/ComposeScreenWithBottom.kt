package com.lionzxy.podlodkamigrationsample.bottombar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.lionzxy.podlodkamigrationsample.feature.forwardFeature

@Composable
fun ComposeBottomNavigation(
    navigationViewModel: NavigationViewModel = viewModel()
) {
    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()
    key(currentDestination?.destination) {
        navigationViewModel.invalidateSelectedTab(currentDestination?.destination)
    }

    Scaffold(
        bottomBar = {
            ComposeBottomNavigation(
                selectedTab = navigationViewModel.selectedTab,
                onSelected = { tab ->
                    val topLevelNavOptions = navOptions {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = navigationViewModel.selectedTab != tab
                    }
                    navController.navigate(tab.name, topLevelNavOptions)
                }
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = BottomTab.ANDROID.name
        ) {
            BottomTab.values().forEach { tab ->
                navigation(
                    route = tab.name,
                    startDestination = "forward?number={number}&container={container}"
                ) {
                    forwardFeature(navController, defaultContainer = tab.name)
                }
            }
        }
    }

}

@Composable
private fun ComposeBottomNavigation(
    selectedTab: BottomTab,
    onSelected: (BottomTab) -> Unit
) {
    BottomNavigation {
        BottomTab.values().forEach { tab ->
            BottomNavigationItem(
                selected = selectedTab == tab,
                onClick = { onSelected(tab) },
                icon = {
                    Icon(
                        painter = painterResource(tab.iconId),
                        contentDescription = tab.name
                    )
                },
                label = {
                    Text(stringResource(tab.titleId))
                }
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun ComposeBottomNavigationPreview() {
    var selectedTab by remember { mutableStateOf(BottomTab.ANDROID) }
    ComposeBottomNavigation(
        selectedTab = selectedTab,
        onSelected = {
            selectedTab = it
        }
    )
}