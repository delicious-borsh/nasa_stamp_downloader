package com.ponykamni.spacestamploader.main

import com.ponykamni.spacestamploader.main.api.SpaceStampLoader
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Main {

    companion object {

        private const val LOG_TAG = "com.ponykamni.main.Main"

        private val spaceStampLoader = SpaceStampLoader()

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                spaceStampLoader.getStamps()
            }
        }
    }
}