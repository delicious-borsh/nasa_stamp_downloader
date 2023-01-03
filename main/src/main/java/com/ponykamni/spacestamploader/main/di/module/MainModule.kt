package com.ponykamni.spacestamploader.main.di.module;

import com.google.gson.Gson
import com.ponykamni.spacestamploader.cache.CacheRepository
import com.ponykamni.spacestamploader.cache.di.CacheFeature
import com.ponykamni.spacestamploader.cache.di.CacheFeatureImpl
import com.ponykamni.spacestamploader.imageprocessor.PdfConverter
import com.ponykamni.spacestamploader.imageprocessor.di.PdfConverterFeature
import com.ponykamni.spacestamploader.imageprocessor.di.PdfConverterFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface MainModule {

    @Binds
    fun bindCacheFeature(impl: CacheFeatureImpl): CacheFeature

    @Binds
    fun bindPdfConverterFeature(impl: PdfConverterFeatureImpl): PdfConverterFeature

    companion object {

        @Provides
        fun provideCacheRepository(feature: CacheFeature): CacheRepository =
            feature.getCacheRepository()

        @Provides
        fun providePdfConverter(feature: PdfConverterFeature): PdfConverter =
            feature.getPdfConverter()

        @Provides
        fun provideGson(): Gson = Gson()
    }
}