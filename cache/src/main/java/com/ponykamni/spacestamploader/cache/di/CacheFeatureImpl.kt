package com.ponykamni.spacestamploader.cache.di;

import com.google.gson.Gson
import com.ponykamni.spacestamploader.cache.di.module.CacheModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

class CacheFeatureImpl @Inject constructor(
    private val gson: Gson,
) : CacheFeature by DaggerCacheFeatureComponent.factory()
    .create(
        gson,
    )

@Component(
    modules = [
        CacheModule::class,
    ],
)
internal interface CacheFeatureComponent : CacheFeature {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance gson: Gson,
        ): CacheFeature
    }
}