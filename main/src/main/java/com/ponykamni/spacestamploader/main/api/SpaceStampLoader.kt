package com.ponykamni.spacestamploader.main.api

interface SpaceStampLoader {

    suspend fun getStamps(): List<Stamp>
}