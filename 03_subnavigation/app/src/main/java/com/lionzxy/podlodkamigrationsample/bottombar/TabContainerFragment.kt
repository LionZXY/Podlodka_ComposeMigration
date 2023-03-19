package com.lionzxy.podlodkamigrationsample.bottombar

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lionzxy.podlodkamigrationsample.feature.forwardFeature
import com.lionzxy.podlodkamigrationsample.utils.BackButtonListener
import com.lionzxy.podlodkamigrationsample.utils.ComposeFragment

class TabContainerFragment : ComposeFragment(), BackButtonListener {
    private val containerName: String
        get() = requireArguments().getString(EXTRA_NAME)!!

    private var navController: NavController? = null

    @Composable
    override fun RenderView() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "forward?number={number}"
        ) {
            forwardFeature(navController, containerName)
        }
    }

    override fun onBackPressed(): Boolean {
        return navController?.popBackStack() ?: false
    }

    companion object {
        private const val EXTRA_NAME = "tcf_extra_name"

        fun getNewInstance(name: String?) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                }
            }
    }
}