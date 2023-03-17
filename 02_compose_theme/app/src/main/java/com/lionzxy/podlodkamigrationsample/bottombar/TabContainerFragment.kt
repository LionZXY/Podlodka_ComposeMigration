package com.lionzxy.podlodkamigrationsample.bottombar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lionzxy.podlodkamigrationsample.App
import com.lionzxy.podlodkamigrationsample.R
import com.lionzxy.podlodkamigrationsample.feature.ForwardFragment
import com.lionzxy.podlodkamigrationsample.utils.BackButtonListener
import com.lionzxy.podlodkamigrationsample.utils.RouterProvider

/**
 * Created by terrakok 25.11.16
 */
class TabContainerFragment : Fragment(), RouterProvider, BackButtonListener {

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.ftc_container, childFragmentManager)
    }

    private val containerName: String
        get() = requireArguments().getString(EXTRA_NAME)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val cicerone: Cicerone<Router>
        get() = App.INSTANCE.ciceroneHolder.getCicerone(containerName)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            cicerone.router.replaceScreen(FragmentScreen {
                ForwardFragment.getNewInstance(containerName, 0)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override val router: Router
        get() = cicerone.router

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.ftc_container)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            true
        } else {
            (activity as RouterProvider?)!!.router.exit()
            true
        }
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