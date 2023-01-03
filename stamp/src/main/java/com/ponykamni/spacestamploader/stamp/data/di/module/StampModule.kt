package com.ponykamni.spacestamploader.stamp.data.di.module;

import com.ponykamni.spacestamploader.stamp.data.StampRepository
import com.ponykamni.spacestamploader.stamp.data.StampRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface StampModule {

    @Binds
    fun bindStampRepository(impl: StampRepositoryImpl): StampRepository
}