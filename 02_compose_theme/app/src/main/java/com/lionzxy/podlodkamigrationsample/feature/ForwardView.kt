package com.lionzxy.podlodkamigrationsample.feature

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface ForwardView : MvpView {
    @AddToEndSingle
    fun onChainSetUp(chain: String)
}