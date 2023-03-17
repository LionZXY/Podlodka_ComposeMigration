package com.lionzxy.podlodkamigrationsample.bottombar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lionzxy.podlodkamigrationsample.R
import com.lionzxy.podlodkamigrationsample.utils.BackButtonListener
import com.lionzxy.podlodkamigrationsample.utils.RouterProvider
import com.lionzxy.podlodkamigrationsample.utils.requireRouter

/**
 * Created by terrakok 25.11.16
 */
class BottomNavigationFragment : Fragment(), RouterProvider, BackButtonListener {

    private lateinit var bottomNavigationBar: BottomNavigationBar

    override val router: Router
        get() = requireRouter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationBar =
            view.findViewById<View>(R.id.ab_bottom_navigation_bar) as BottomNavigationBar

        initViews()
        if (savedInstanceState == null) {
            bottomNavigationBar.selectTab(0, true)
        }
    }

    private fun initViews() {
        bottomNavigationBar
            .addItem(BottomNavigationItem(R.drawable.ic_android_white_24dp, R.string.tab_android))
            .addItem(BottomNavigationItem(R.drawable.ic_bug_report_white_24, R.string.tab_bug))
            .addItem(BottomNavigationItem(R.drawable.ic_pets_white_24dp, R.string.tab_dog))
            .initialise()
        bottomNavigationBar.setTabSelectedListener(object :
            BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                when (position) {
                    0 -> selectTab("ANDROID")
                    1 -> selectTab("BUG")
                    2 -> selectTab("DOG")
                }
                bottomNavigationBar.selectTab(position, false)
            }

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {
                onTabSelected(position)
            }
        })
    }

    private fun selectTab(tab: String) {
        val fm = childFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fm.findFragmentByTag(tab)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return
        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.ab_container,
                FragmentScreen {
                    TabContainerFragment.getNewInstance(tab)
                }.createFragment(fm.fragmentFactory), tab
            )
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }


    override fun onBackPressed(): Boolean {
        val currentFragment = childFragmentManager.fragments.find { it.isVisible } ?: return false
        return (currentFragment as? BackButtonListener)?.onBackPressed() ?: false
    }
}