package com.lionzxy.podlodkamigrationsample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lionzxy.podlodkamigrationsample.bottombar.ComposeBottomNavigation
import com.lionzxy.podlodkamigrationsample.feature.forwardFeature
import com.lionzxy.podlodkamigrationsample.utils.AppTheme
import com.lionzxy.podlodkamigrationsample.utils.GlobalNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()


            AppTheme {
                CompositionLocalProvider(GlobalNavController provides navController) {
                    NavHost(
                        navController = navController,
                        startDestination = "bottombar"
                    ) {
                        composable("bottombar") {
                            ComposeBottomNavigation()
                        }
                        forwardFeature(navController, defaultContainer = "Global")
                    }
                }
            }
        }
    }
}