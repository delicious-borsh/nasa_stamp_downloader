package com.ponykamni.spacestamploader.main.di;

import com.ponykamni.spacestamploader.main.api.SpaceStampLoader
import com.ponykamni.spacestamploader.main.di.module.MainModule
import dagger.Component

@Component(
    modules = [
        MainModule::class,
    ],
)
internal interface MainComponent {

    @Component.Factory
    interface Factory {

        fun create(): MainComponent
    }

    fun getSpaceStampLoader(): SpaceStampLoader
}