package com.ponykamni.spacestamploader.cache.di.module;

import com.ponykamni.spacestamploader.cache.CacheRepository
import com.ponykamni.spacestamploader.cache.CacheRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface CacheModule {

    @Binds
    fun bindCacheRepository(impl: CacheRepositoryImpl): CacheRepository
}