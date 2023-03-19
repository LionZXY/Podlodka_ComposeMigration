package com.lionzxy.podlodkamigrationsample.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.github.terrakok.cicerone.Router

class ForwardViewModelFactory(
    private val containerName: String,
    private val number: Int,
    private val navController: NavController
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForwardViewModel(
            containerName = containerName,
            number = number,
            navController = navController
        ) as T
    }
}