package com.ponykamni.spacestamploader.stamp.data.di;

import com.ponykamni.spacestamploader.stamp.data.di.module.StampModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

class StampFeatureImpl @Inject constructor(
) : StampFeature by DaggerStampFeatureComponent.factory()
    .create()

@Component(
    modules = [
        StampModule::class,
    ],
)
internal interface StampFeatureComponent : StampFeature {

    @Component.Factory
    interface Factory {

        fun create(
        ): StampFeature
    }
}