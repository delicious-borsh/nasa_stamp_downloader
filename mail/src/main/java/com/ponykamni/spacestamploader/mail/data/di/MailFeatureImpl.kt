package com.ponykamni.spacestamploader.mail.data.di;

import com.ponykamni.spacestamploader.mail.data.di.module.MailModule
import dagger.Component
import javax.inject.Inject

class MailFeatureImpl @Inject constructor(
) : MailFeature by DaggerMailFeatureComponent.factory()
    .create()

@Component(
    modules = [
        MailModule::class,
    ],
)
internal interface MailFeatureComponent : MailFeature {

    @Component.Factory
    interface Factory {

        fun create(
        ): MailFeature
    }
}