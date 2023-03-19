package com.lionzxy.podlodkamigrationsample.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val GlobalNavController = compositionLocalOf<NavController> { error("No global nav controller") }
