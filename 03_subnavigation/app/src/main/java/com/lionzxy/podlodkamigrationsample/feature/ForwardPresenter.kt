package com.lionzxy.podlodkamigrationsample.feature

import androidx.navigation.NavController
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lionzxy.podlodkamigrationsample.App
import com.lionzxy.podlodkamigrationsample.bottombar.TabContainerFragment
import moxy.MvpPresenter

class ForwardPresenter(
    private val viewState: ForwardView,
    private val container: String,
    private val number: Int,
    private val navController: NavController
) {

    fun onForwardFullScreen() {
        App.INSTANCE.router.navigateTo(FragmentScreen {
            TabContainerFragment.getNewInstance(container)
        })
    }

    fun onForward() {
        navController.toForward(container, number + 1)
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