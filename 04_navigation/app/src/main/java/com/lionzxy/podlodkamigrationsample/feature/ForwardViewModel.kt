package com.lionzxy.podlodkamigrationsample.feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lionzxy.podlodkamigrationsample.App

data class ForwardState(
    val title: String,
    val chain: String = ""
)

class ForwardViewModel(
    containerName: String,
    number: Int,
    navController: NavController
) : ViewModel(), ForwardView {
    var forwardState by mutableStateOf(
        ForwardState(
            title = containerName
        )
    )
        private set

    private val presenter = ForwardPresenter(
        viewState = this,
        container = containerName,
        number = number,
        navController = navController
    )

    fun onForwardFullScreen(navController: NavController) =
        presenter.onForwardFullScreen(navController)

    fun onForward() = presenter.onForward()

    fun onBack() = presenter.onBack()

    override fun onChainSetUp(chain: String) {
        forwardState = forwardState.copy(chain = chain)
    }
}