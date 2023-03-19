package com.lionzxy.podlodkamigrationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lionzxy.podlodkamigrationsample.bottombar.BottomNavigationFragment
import com.lionzxy.podlodkamigrationsample.utils.RouterProvider

class MainActivity : AppCompatActivity(), RouterProvider {
    override val router: Router = App.INSTANCE.router
    private val navigator: Navigator = AppNavigator(this, R.id.fragment_container)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            router.newRootScreen(FragmentScreen {
                BottomNavigationFragment()
            })
        }
    }

    override fun onResume() {
        super.onResume()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.INSTANCE.navigatorHolder.removeNavigator()
    }
}