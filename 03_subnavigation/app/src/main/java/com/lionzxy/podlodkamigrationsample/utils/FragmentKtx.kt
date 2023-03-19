package com.lionzxy.podlodkamigrationsample.utils

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router

fun Fragment.requireRouter(): Router {
    if (parentFragment != null && parentFragment is RouterProvider) {
        return (parentFragment as RouterProvider).router
    }
    if (activity != null && activity is RouterProvider) {
        return (activity as RouterProvider).router
    }
    error("Parent activity and fragment is not RouterProvider")
}