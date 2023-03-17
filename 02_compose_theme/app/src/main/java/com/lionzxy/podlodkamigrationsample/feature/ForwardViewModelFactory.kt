package com.lionzxy.podlodkamigrationsample.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router

class ForwardViewModelFactory(
    private val containerName: String,
    private val number: Int,
    private val router: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForwardViewModel(
            containerName = containerName,
            number = number,
            router = router
        ) as T
    }
}