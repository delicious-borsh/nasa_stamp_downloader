package com.ponykamni.spacestamploader.main

import com.ponykamni.spacestamploader.main.di.DaggerMainComponent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

internal class Main {

    companion object {

        private const val LOG_TAG = "com.ponykamni.main.Main"

        private val component = DaggerMainComponent.factory().create()

        private val spaceStampLoader = component.getSpaceStampLoader()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                spaceStampLoader.getStamps()
            }
        }
    }
}