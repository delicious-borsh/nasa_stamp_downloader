package com.ponykamni.spacestamploader.imageprocessor.di.module;

import com.ponykamni.spacestamploader.imageprocessor.PdfConverter
import com.ponykamni.spacestamploader.imageprocessor.PdfConverterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface PdfConverterModule {

    @Binds
    fun bindPdfConverter(impl: PdfConverterImpl): PdfConverter
}