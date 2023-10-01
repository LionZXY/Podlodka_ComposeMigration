package com.lionzxy.podlodkamigrationsample.bottombar

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.lionzxy.podlodkamigrationsample.R

enum class BottomTab(@StringRes val titleId: Int, @DrawableRes val iconId: Int) {
    ANDROID(R.string.tab_android, R.drawable.ic_android_white_24dp),
    BUG(R.string.tab_bug, R.drawable.ic_bug_report_white_24),
    DOG(R.string.tab_dog, R.drawable.ic_pets_white_24dp)
}

class NavigationViewModel : ViewModel() {
    var selectedTab by mutableStateOf(BottomTab.ANDROID)

    fun invalidateSelectedTab(currentDestination: NavDestination?) {
        val currentTab = BottomTab.values()
            .find { tab ->
                currentDestination.isTopLevelDestinationInHierarchy(tab)
            }
        if (currentTab == null) {
            Log.i("NavigationViewModel¬", "Can't find current tab for $currentDestination")
            return
        }
        selectedTab = currentTab
    }

}

/**
 * Copy from https://github.com/android/nowinandroid/blob/e63394248b23f2a138f6ed333e5711b898d24a40/app/src/main/java/com/google/samples/apps/nowinandroid/ui/NiaApp.kt#L270
 */
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomTab) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false