package com.ponykamni.spacestamploader.cache.di;

import com.ponykamni.spacestamploader.cache.CacheRepository

interface CacheFeature {

    fun getCacheRepository(): CacheRepository
}