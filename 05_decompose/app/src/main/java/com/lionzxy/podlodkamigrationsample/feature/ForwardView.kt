package com.lionzxy.podlodkamigrationsample.feature

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ForwardView : MvpView {
    @AddToEndSingle
    fun onChainSetUp(chain: String)
}