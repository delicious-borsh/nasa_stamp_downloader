package com.ponykamni.spacestamploader.main

import com.ponykamni.spacestamploader.main.api.SpaceStampLoader
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

internal class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            launch {

                val spaceStampLoader = SpaceStampLoader.create()

                spaceStampLoader.getStamps()
            }
        }
    }
}