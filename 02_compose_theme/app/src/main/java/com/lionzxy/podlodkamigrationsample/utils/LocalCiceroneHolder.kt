package com.lionzxy.podlodkamigrationsample.utils

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

/**
 * Created by terrakok 27.11.16
 */
class LocalCiceroneHolder {
    private val containers = HashMap<String, Cicerone<Router>>()

    fun getCicerone(containerTag: String): Cicerone<Router> =
            containers.getOrPut(containerTag) {
                Cicerone.create()
            }
}