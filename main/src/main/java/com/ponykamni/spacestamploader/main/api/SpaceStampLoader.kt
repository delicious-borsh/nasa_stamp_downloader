package com.ponykamni.spacestamploader.main.api

import com.ponykamni.spacestamploader.main.di.DaggerMainComponent

interface SpaceStampLoader {

    suspend fun getStamps(): List<Stamp>

    companion object {

        fun create(): SpaceStampLoader =
            DaggerMainComponent.factory().create().getSpaceStampLoader()
    }
}