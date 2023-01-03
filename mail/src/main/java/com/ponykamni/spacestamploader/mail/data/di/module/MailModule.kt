package com.ponykamni.spacestamploader.mail.data.di.module;

import com.ponykamni.spacestamploader.mail.data.MailRepository
import com.ponykamni.spacestamploader.mail.data.MailRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface MailModule {

    @Binds
    fun bindMailRepository(impl: MailRepositoryImpl): MailRepository
}