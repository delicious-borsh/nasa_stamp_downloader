package com.ponykamni.spacestamploader.imageprocessor.di;

import com.ponykamni.spacestamploader.imageprocessor.di.module.PdfConverterModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

class PdfConverterFeatureImpl @Inject constructor(
) : PdfConverterFeature by DaggerPdfConverterFeatureComponent.factory()
    .create()

@Component(
    modules = [
        PdfConverterModule::class,
    ],
)
internal interface PdfConverterFeatureComponent : PdfConverterFeature {

    @Component.Factory
    interface Factory {

        fun create(
        ): PdfConverterFeature
    }
}