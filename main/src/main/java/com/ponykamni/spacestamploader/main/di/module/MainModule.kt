package com.ponykamni.spacestamploader.main.di.module;

import com.google.gson.Gson
import com.ponykamni.spacestamploader.cache.CacheRepository
import com.ponykamni.spacestamploader.cache.di.CacheFeature
import com.ponykamni.spacestamploader.cache.di.CacheFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface MainModule {

    @Binds
    fun bindCacheFeature(impl: CacheFeatureImpl): CacheFeature

    companion object {

        @Provides
        fun provideCacheRepository(feature: CacheFeature): CacheRepository =
            feature.getCacheRepository()

        @Provides
        fun provideGson(): Gson = Gson()
    }
}