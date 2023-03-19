package com.lionzxy.podlodkamigrationsample.utils

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

val LocalPallet = compositionLocalOf<AppPallet> { error("No local pallet") }

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val pallet = remember { AppPallet() }
    val textStyle = remember {
        TextStyle(
            fontSize = 32.sp
        )
    }
    CompositionLocalProvider(
        LocalPallet provides pallet,
        LocalTextStyle provides textStyle
    ) {
        content()
    }
}