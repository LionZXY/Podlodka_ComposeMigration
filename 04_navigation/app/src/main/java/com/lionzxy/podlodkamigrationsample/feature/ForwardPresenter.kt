package com.lionzxy.podlodkamigrationsample.feature

import androidx.navigation.NavController
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lionzxy.podlodkamigrationsample.App

class ForwardPresenter(
    private val viewState: ForwardView,
    private val container: String,
    private val number: Int,
    private val navController: NavController
) {

    fun onForwardFullScreen(navController: NavController) {
        navController.toForward(0)
    }

    fun onForward() {
        navController.toForward(number + 1, container)
    }

    fun onBack() {
        navController.popBackStack()
    }

    init {
        viewState.onChainSetUp(createChain(number))
    }

    private fun createChain(number: Int): String {
        var chain = "[0]"
        for (i in 0 until number) {
            chain += "➔" + (i + 1)
        }
        return chain
    }
}