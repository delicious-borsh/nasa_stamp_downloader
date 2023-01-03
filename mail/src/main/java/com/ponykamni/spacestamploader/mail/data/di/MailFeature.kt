package com.ponykamni.spacestamploader.mail.data.di;

import com.ponykamni.spacestamploader.mail.data.MailRepository

interface MailFeature {

    fun getMailRepository(): MailRepository
}