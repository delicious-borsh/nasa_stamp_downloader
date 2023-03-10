package com.ponykamni.spacestamploader.main.di.module;

import com.google.gson.Gson
import com.ponykamni.spacestamploader.cache.CacheRepository
import com.ponykamni.spacestamploader.cache.di.CacheFeature
import com.ponykamni.spacestamploader.cache.di.CacheFeatureImpl
import com.ponykamni.spacestamploader.imageprocessor.PdfConverter
import com.ponykamni.spacestamploader.imageprocessor.di.PdfConverterFeature
import com.ponykamni.spacestamploader.imageprocessor.di.PdfConverterFeatureImpl
import com.ponykamni.spacestamploader.mail.data.MailRepository
import com.ponykamni.spacestamploader.mail.data.di.MailFeature
import com.ponykamni.spacestamploader.mail.data.di.MailFeatureImpl
import com.ponykamni.spacestamploader.main.SpaceStampLoaderImpl
import com.ponykamni.spacestamploader.main.api.SpaceStampLoader
import com.ponykamni.spacestamploader.stamp.data.StampRepository
import com.ponykamni.spacestamploader.stamp.data.di.StampFeature
import com.ponykamni.spacestamploader.stamp.data.di.StampFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface MainModule {

    @Binds
    fun bindCacheFeature(impl: CacheFeatureImpl): CacheFeature

    @Binds
    fun bindPdfConverterFeature(impl: PdfConverterFeatureImpl): PdfConverterFeature

    @Binds
    fun bindMailFeature(impl: MailFeatureImpl): MailFeature

    @Binds
    fun bindStampFeature(impl: StampFeatureImpl): StampFeature

    @Binds
    fun bindSpaceStampLoader(impl: SpaceStampLoaderImpl): SpaceStampLoader

    companion object {

        @Provides
        fun provideCacheRepository(feature: CacheFeature): CacheRepository =
            feature.getCacheRepository()

        @Provides
        fun providePdfConverter(feature: PdfConverterFeature): PdfConverter =
            feature.getPdfConverter()

        @Provides
        fun provideMailRepository(feature: MailFeature): MailRepository =
            feature.getMailRepository()

        @Provides
        fun provideStampRepository(feature: StampFeature): StampRepository =
            feature.getStampRepository()

        @Provides
        fun provideGson(): Gson = Gson()
    }
}