package com.lionzxy.podlodkamigrationsample.feature

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lionzxy.podlodkamigrationsample.App
import moxy.MvpPresenter

class ForwardPresenter(
    private val container: String,
    private val number: Int,
    private val router: Router
) : MvpPresenter<ForwardView>() {

    fun onForwardFullScreen() {
        App.INSTANCE.router.navigateTo(FragmentScreen {
            ForwardFragment.getNewInstance(container, 0)
        })
    }

    fun onForward() {
        router.navigateTo(FragmentScreen {
            ForwardFragment.getNewInstance(container, number + 1)
        })
    }

    fun onBack() = router.exit()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
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